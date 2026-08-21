package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;
import pl.tlewandster.ffwork.payment.Payment;

import java.time.Duration;
import java.time.LocalDateTime;

public class Booking {
    private static long bookCounter;
    protected LocalDateTime start;
    protected LocalDateTime end;
    private String id;
    private User user;
    private Resource resource;
    private BookingStatus status;
    private Money calculatedPrice;
    private Payment payment;

    public Booking(User user, Resource resource, LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time is before start time");
        }
        this.user = user;
        this.resource = resource;
        this.start = start;
        this.end = end;
        this.status = BookingStatus.PENDING;
        ++bookCounter;
    }

    public static long getBookCounter() {
        return bookCounter;
    }

    public Money getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(Money calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus newStatus) {
        if (this.status == BookingStatus.PENDING && newStatus == BookingStatus.COMPLETED) {
            throw new IllegalStateException("The booking status cannot change from PENDING to COMPLETED");
        }
        if (this.status == BookingStatus.CONFIRMED && newStatus == BookingStatus.PENDING) {
            throw new IllegalStateException("The booking status cannot change from CONFIRMED to PENDING");
        }
        this.status = newStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Od: " + start + ", do: " + end + ", id: " + id + "\nUżytkownik: " + user + "\nZasób: " + resource + "\nStatus: " + status + ", cena: " + calculatedPrice + ", płatność: " + payment;
    }

    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    public Resource getResource() {
        return this.resource;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public User getUser() {
        return user;
    }
}
