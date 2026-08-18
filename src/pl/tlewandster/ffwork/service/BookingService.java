package pl.tlewandster.ffwork.service;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.domain.BookingStatus;
import pl.tlewandster.ffwork.domain.Resource;
import pl.tlewandster.ffwork.domain.User;
import pl.tlewandster.ffwork.pricing.PricingPolicy;
import pl.tlewandster.ffwork.repo.BookingRepository;
import pl.tlewandster.ffwork.repo.ResourceRepository;
import pl.tlewandster.ffwork.repo.UserRepository;

import java.time.LocalDateTime;

public class BookingService {
    private final UserRepository users;
    private final ResourceRepository resources;
    private final BookingRepository bookings;
    private final PricingPolicy pricingPolicy;

    public BookingService(UserRepository users, ResourceRepository resources, BookingRepository bookings, PricingPolicy pricingPolicy) {
        this.users = users;
        this.resources = resources;
        this.bookings = bookings;
        this.pricingPolicy = pricingPolicy;
    }

    Booking book(User user, Resource resource, LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalStateException("The end date cannot be before the start date");
        }
        return null;
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
}
