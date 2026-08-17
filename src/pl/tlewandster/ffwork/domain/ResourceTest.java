package pl.tlewandster.ffwork.domain;

import pl.tlewandster.ffwork.money.Money;

public class ResourceTest {
    static void main() {
        Resource smallHall = new Room("Mała sala", 10, 100);
        Resource hotDesk = new Desk("Hot biurko", "hot", 25);
        Resource projector = new Device("Projektor", 2, 40);

        System.out.println(smallHall.describe());
        System.out.println(hotDesk.describe());
        System.out.println(projector.describe());
    }
}
