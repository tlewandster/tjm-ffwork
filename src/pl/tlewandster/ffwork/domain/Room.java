package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

import java.util.Set;

public class Room extends Resource {
    private int seats;
    private Set<String> equipment;

    public Room(String name, int seats, double customHourlyRate) {
        super(name, customHourlyRate);
        if (seats < 0) {
            throw new IllegalArgumentException("Seats cannot be negative");
        }
        this.seats = seats;
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
                        Ilość miejsc: %d
                        Wyposażenie: %s
                        Stawka za godzinę: %s
                        """,
                this.getName(), this.seats, this.showEquipment(), this.hourlyRate()
        );
    }

    private String showEquipment() {
        if (equipment == null) {
            return "brak";
        } else {
            return equipment.toString();
        }
    }
}
