package pl.tlewandster.ffwork.service;

import pl.tlewandster.ffwork.domain.*;
import pl.tlewandster.ffwork.pricing.PricingPolicy;
import pl.tlewandster.ffwork.repo.BookingRepository;
import pl.tlewandster.ffwork.repo.InMemoryUserRepository;
import pl.tlewandster.ffwork.repo.ResourceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class BookingService {

    private final InMemoryUserRepository users;
    private final ResourceRepository resources;
    private final BookingRepository bookings;
    private final PricingPolicy pricingPolicy;

    public BookingService(InMemoryUserRepository users, ResourceRepository resources, BookingRepository bookings, PricingPolicy pricingPolicy) {
        this.users = users;
        this.resources = resources;
        this.bookings = bookings;
        this.pricingPolicy = pricingPolicy;
    }

    Booking book(String userEmail, String resourceName, String startIso, String endIso) {
        LocalDateTime start = LocalDateTime.parse(startIso);
        LocalDateTime end = LocalDateTime.parse(endIso);
        User user = users.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("That user does not exist in the database"));
        Resource resource = resources.findByName((resourceName)).orElseThrow(() -> new IllegalArgumentException("That resource does not exist in the database"));
        if (start.isAfter(end)) {
            throw new IllegalStateException("The end date cannot be before the start date");
        }
        checkForCollisions(resource, start, end);
        Booking newBooking = new Booking(user, resource, start, end);
        newBooking.setCalculatedPrice(pricingPolicy.price(newBooking));
        String newBookingId = "BK-" + start.format(DateTimeFormatter.BASIC_ISO_DATE) + "-" + Booking.getBookCounter();
        return newBooking;
    }

    private void checkForCollisions(Resource resource, LocalDateTime start, LocalDateTime end) {
        if (resource instanceof Room || resource instanceof Desk) {
            boolean isOverlapped = bookings.findAll().stream()
                    .filter(booking -> booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.PENDING)
                    .anyMatch(booking -> start.isBefore(booking.getEnd()) && booking.getStart().isBefore(end));
            if (isOverlapped) {
                throw new IllegalArgumentException("Reservation dates cannot overlap");
            }
        }
        if (resource instanceof Device device) {
            long reservedCopies = bookings.findAll().stream()
                    .filter(booking -> start.isBefore(booking.getEnd()) && booking.getStart().isBefore(end))
                    .count();
            if (reservedCopies == device.getQuantity()) {
                throw new IllegalStateException("All devices are reserved");
            }
        }
    }

    public void confirm(String bookingId) {
        bookings.findById(bookingId).ifPresent(booking -> booking.setStatus(BookingStatus.CONFIRMED));
    }

    public void cancel(String bookingId) {
        bookings.findById(bookingId).ifPresent(booking -> booking.setStatus(BookingStatus.CANCELLED));
    }

    public void complete(String bookingId) {
        bookings.findById(bookingId).ifPresent(booking -> booking.setStatus(BookingStatus.COMPLETED));
    }

    public String listAll(){
        return bookings.findAll().stream()
                .collect(Collectors.groupingBy(Booking::getId)).toString();
    }
}
