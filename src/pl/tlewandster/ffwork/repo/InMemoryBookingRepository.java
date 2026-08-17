package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookingRepository implements BookingRepository {
    private static final List<Booking> bookings = new ArrayList<>();

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public Optional<Booking> findById(String id) {

        return bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Booking> findAll() {

        return List.copyOf(bookings);
    }
}
