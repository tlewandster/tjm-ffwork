package pl.tlewandster.ffwork.domain;

import java.time.LocalDateTime;

public class BookingTest {
    static void main() {
        Booking booking = new Booking();
        booking.start = LocalDateTime.of(2026, 8, 1, 8, 0, 0);
        booking.end = LocalDateTime.of(2026, 8, 1, 16, 0, 0);
        System.out.println(booking.durationMinutes());
    }
}
