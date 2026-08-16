package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

public class HappyHoursPricing implements PricingPolicy {
    private static double happyHoursDiscount = 0.3;

    @Override
    public Money price(Booking booking) {
        int hourOfStart = booking.getStart().getHour();
        if (hourOfStart >= 14 && hourOfStart <= 16) {
            return PricingPolicy.super.price(booking).multiply(1 - happyHoursDiscount);
        } else {
            return PricingPolicy.super.price(booking);
        }
    }
}
