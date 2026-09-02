package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

import java.math.BigDecimal;

public class HappyHoursPricing implements PricingPolicy {
    private static final BigDecimal happyHoursDiscount = BigDecimal.valueOf(0.3);

    @Override
    public Money price(Booking booking) {
        int hourOfStart = booking.getStart().getHour();
        boolean isHappyHours = hourOfStart >= 14 && hourOfStart <= 16;
        return PricingPolicy.super.price(booking).multiply(
                isHappyHours ? BigDecimal.ONE.subtract(happyHoursDiscount) : BigDecimal.ONE);
    }
}
