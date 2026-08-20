package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Desk extends Resource {
    private final DeskType type;

    public Desk(String name, String type, Number customHourlyRate) {
        super(name, customHourlyRate);
        this.type = DeskType.valueOf(type.toUpperCase());
    }

    public Desk(String name, String type) {
        this(name, type, null);
    }

    @Override
    public String toString() {
        return "Desk{" + "type=" + type + "} " + super.toString();
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of(80);
    }

    @Override
    public String describe() {
        return String.format("""
                Nazwa: Biurko / %s
                Typ biurka: %s
                Stawka za godzinę: %s
                """, this.getName(), this.type, this.hourlyRate());
    }

    public enum DeskType {
        HOT, FIXED;
    }
}
