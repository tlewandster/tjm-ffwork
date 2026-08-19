package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public abstract class Resource {
    private final String name;
    private final Money customHourlyRate;

    public Resource(String name) {
        this(name, null);
    }

    public Resource(String name, Number customHourlyRate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
        if (customHourlyRate == null) {
            this.customHourlyRate = null;
        } else {
            this.customHourlyRate = Money.of(customHourlyRate.toString());
        }
    }

    public String getName() {
        return name;
    }

    protected abstract Money baseRatePerHour();

    public abstract String describe();

    public Money hourlyRate() {
        if (this.customHourlyRate != null) {
            return this.customHourlyRate;
        } else {
            return this.baseRatePerHour();
        }
    }
}