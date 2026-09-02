package pl.tlewandster.ffwork.pricing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.money.Money;

import java.math.BigDecimal;

public class HappyHoursPricing implements PricingPolicy {
    private static final BigDecimal HAPPY_HOUR_DISCOUNT = BigDecimal.valueOf(0.3);
    private static final int HOUR_14_00 = 14;
    private static final int HOUR_16_00 = 16;

    @Override
    public Money price(Booking booking) {
        Money basePrice = PricingPolicy.super.price(booking);
        return isHappyHours(booking) ? basePrice.withDiscount(HAPPY_HOUR_DISCOUNT) : basePrice;
    }

    private boolean isHappyHours(Booking booking) {
        int hourOfStart = booking.getStart().getHour();
        return hourOfStart >= HOUR_14_00 && hourOfStart <= HOUR_16_00;
    }
}

