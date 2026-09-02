package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Desk extends Resource {
    private final double BASE_RATE_PER_HOUR = 80.00;
    private final DeskType type;

    public Desk(String name, String type) {
        this(name, type, null);
    }

    public Desk(String name, String type, Number customHourlyRate) {
        super(name, customHourlyRate);
        this.type = DeskType.valueOf(type.toUpperCase());
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of(BASE_RATE_PER_HOUR);
    }

    @Override
    public String describe() {
        return String.format("""
                Desk name:  %s
                Desk type: %s
                Hourly rate: %s
                """, this.getName(), this.type, this.hourlyRate());
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", type: " + this.type + ", hourly rate: " + this.hourlyRate();
    }

    public enum DeskType {
        HOT, FIXED;
    }
}
