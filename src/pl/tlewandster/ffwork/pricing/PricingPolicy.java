package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

public interface PricingPolicy {
    default Money price(Booking booking) {
        Money hourlyRate = booking.getResource().hourlyRate();
        return hourlyRate.multiply(booking.durationMinutes()).divide(60);
    }
}
