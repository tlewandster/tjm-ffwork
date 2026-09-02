package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    void add(Booking book);

    Optional<Booking> findById(String id);

    List<Booking> findAll();
}
