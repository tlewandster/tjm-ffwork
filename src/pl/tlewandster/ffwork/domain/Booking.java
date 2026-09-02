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

    public void confirm() {
        if (this.status != BookingStatus.PENDING) {
            throw new IllegalStateException("The booking status cannot change from " + this.status.name() + " to CONFIRMED");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }

    public void complete() {
        if (this.status != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("The booking status cannot change from " + this.status.name() + " to COMPLETED");
        }
        this.status = BookingStatus.COMPLETED;
    }

    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Start: " + start + ", End: " + end + ", id: " + id + "\nUser: " + user + "\nResource: " + resource + "\nStatus: " + status + ", price: " + calculatedPrice + ", payment: " + payment;
    }
}
