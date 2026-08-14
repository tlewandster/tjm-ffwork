package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Device extends Resource{

    private int quantity;

    public Device(String name, int quantity, double customHourlyRate) {
        super(name, customHourlyRate);
        if (quantity<0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        this.quantity = quantity;
    }

    @Override
    protected Money baseRatePerHour() {
        return null;
    }

    @Override
    public String describe() {
        return String.format(
                """
                        Nazwa: %s
                        Ilość urządzeń: %d
                        Stawka za godzinę: %s
                        """,
                this.getName(), this.quantity, this.hourlyRate()
        );
    }
}
