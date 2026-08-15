package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class Desk extends Resource {
    private DeskType type;

    public Desk(String name, String type, double customHourlyRate) {
        super(name, customHourlyRate);
        this.type = DeskType.valueOf(type.toUpperCase());
    }

    @Override
    protected Money baseRatePerHour() {
        return null;
    }

    @Override
    public String describe() {
        return String.format(
                """
                        Nazwa: Biurko / %s
                        Typ biurka: %s
                        Stawka za godzinę: %s
                        """,
                this.getName(), this.type, this.hourlyRate()
        );
    }

    public enum DeskType {
        HOT, FIXED;
    }
}
