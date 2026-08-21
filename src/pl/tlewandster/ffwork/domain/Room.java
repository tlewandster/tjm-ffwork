package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

import java.util.Set;

public class Room extends Resource {
    private final int seats;
    private final Set<String> equipment;

    public Room(String name, int seats, Set<String> equipment, Number customHourlyRate) {
        super(name, customHourlyRate);
        if (seats < 0) {
            throw new IllegalArgumentException("Seats cannot be negative");
        }
        this.seats = seats;
        this.equipment = equipment;
    }

    public Room(String name, int seats, Set<String> equipment) {
        this(name, seats, equipment, null);
    }

    public Room(String name, int seats, Number customHourlyRate) {
        this(name, seats, null, customHourlyRate);
    }

    public Room(String name, int seats) {
        this(name, seats, null, null);
    }

    @Override
    public String toString() {
        return "Nazwa: " + this.getName() + ", ilość miejsc: " + this.seats + ", wyposażenie: " + (this.equipment == null ? "brak" : this.equipment) + ", stawka za godzinę: " + this.hourlyRate();
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of(150);
    }

    @Override
    public String describe() {
        return String.format("""
                Nazwa: Sala / %s
                Ilość miejsc: %d
                Wyposażenie: %s
                Stawka za godzinę: %s
                """, this.getName(), this.seats, this.showEquipment(), this.hourlyRate());
    }

    private String showEquipment() {
        if (equipment == null) {
            return "brak";
        } else {
            return equipment.toString();
        }
    }
}
