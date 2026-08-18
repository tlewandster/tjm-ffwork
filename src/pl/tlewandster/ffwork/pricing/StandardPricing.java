package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

public class StandardPricing implements PricingPolicy{
    @Override
    public Money price(Booking booking) {
        return PricingPolicy.super.price(booking);
    }
}
