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

    public Booking(String id, User user, Resource resource, LocalDateTime start, LocalDateTime end, Money calculatedPrice) {
        if (end.isBefore(start)){
            throw new IllegalArgumentException("End time is before start time");
        }
        this.id = id;
        this.user = user;
        this.resource = resource;
        this.start = start;
        this.end = end;
        this.calculatedPrice = calculatedPrice;
        this.status = BookingStatus.PENDING;
    }


    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }
}
