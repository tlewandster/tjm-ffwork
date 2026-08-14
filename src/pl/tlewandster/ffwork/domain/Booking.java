package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

import java.time.Duration;
import java.time.LocalDateTime;

public class Booking {
    private static long bookCounter;
    private String id;
    private User user;
    private Resource resource;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private Money calculatedPrice;
    /* TODO Payment class
    private Payment payment;*/

    //TODO Overloaded constructors by factory and invariant validations.

    private long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }
}
