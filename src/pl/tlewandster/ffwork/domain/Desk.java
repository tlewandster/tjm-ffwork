package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Desk extends Resource {
    private DeskType type;

    public Desk(String name, DeskType type, Money customHourlyRate) {
        super(name, customHourlyRate);
        this.type = type;
    }

    @Override
    protected Money baseRatePerHour() {
        return null;
    }

    @Override
    public String describe() {
        return "";
    }

    public enum DeskType {
        HOT, FIXED;
    }
}
