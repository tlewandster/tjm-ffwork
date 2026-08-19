package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

public interface PricingPolicy {
    default Money price(Booking booking) {
        Money hourlyRate = booking.getResource().hourlyRate();
        Money pricePerMinute = hourlyRate.divide(60);
        return pricePerMinute.multiply(booking.durationMinutes());
    }
}
