package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

import java.util.Set;

public class ResourceTest {
    static void main() {
        Resource smallHall1 = new Room("Mała sala", 10, 100);
        Resource smallHall2 = new Room("Mała sala z wyposażeniem", 10, Set.of("pełne wyposażenie"),100);
        Resource bigHall = new Room("Duża sala", 15);
        Resource hotDesk = new Desk("Hot biurko", "hot", 25);
        Resource projector = new Device("Projektor", 2, 40);

        System.out.println(smallHall1.describe());
        System.out.println(smallHall2.describe());
        System.out.println(hotDesk.describe());
        System.out.println(projector.describe());
    }
}
