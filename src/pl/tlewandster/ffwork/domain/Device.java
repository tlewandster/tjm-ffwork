package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Device extends Resource {

    private final int quantity;

    public Device(String name, int quantity, Number customHourlyRate) {
        super(name, customHourlyRate);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        this.quantity = quantity;
    }

    public Device(String name, int quantity) {
        this(name, quantity, null);
    }

    @Override
    public String toString() {
        return "Device{" + "quantity=" + quantity + "} " + super.toString();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of(30);
    }

    @Override
    public String describe() {
        return String.format("""
                Nazwa: %s
                Ilość urządzeń: %d
                Stawka za godzinę: %s
                """, this.getName(), this.quantity, this.hourlyRate());
    }
}
