package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

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

    public String getId() {
        return id;
    }

    public Resource getResource() {
        return resource;
    }
    /* TODO Payment class
    private Payment payment;*/

    //TODO Overloaded constructors by factory and invariant validations.

    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }
}
