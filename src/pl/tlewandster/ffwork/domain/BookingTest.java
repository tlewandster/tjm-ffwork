package pl.tlewandster.ffwork.domain;

import java.time.LocalDateTime;

public class BookingTest {
    static void main() {
        User user1 = new IndividualUser("kunefal@op.pl", "Jaś Kunefał", "12345678901");
        Resource hotDesk = new Desk("Hot biurko", "hot", 25);
        LocalDateTime start = LocalDateTime.of(2026, 5, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2026, 5, 1, 13, 0);

        try {
            Booking booking1 = new Booking(user1, hotDesk, end, start);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
        System.out.println();

        Booking booking = new Booking(user1, hotDesk, start, end);
        System.out.println(booking);
        System.out.println();

        System.out.println(Booking.getBookCounter());
        System.out.println();

        System.out.println(booking.getStatus());
        try {
            booking.complete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(booking.getStatus());
        booking.confirm();
        System.out.println(booking.getStatus());
        booking.complete();
        System.out.println(booking.getStatus());
        System.out.println();

        System.out.println(booking.durationMinutes());
        System.out.println();
    }
}

