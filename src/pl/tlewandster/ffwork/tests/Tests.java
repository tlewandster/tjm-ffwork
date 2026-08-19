package pl.tlewandster.ffwork.tests;

import pl.tlewandster.ffwork.billing.Billable;
import pl.tlewandster.ffwork.billing.Invoice;
import pl.tlewandster.ffwork.domain.*;
import pl.tlewandster.ffwork.payment.CardPayment;
import pl.tlewandster.ffwork.pricing.PricingPolicy;
import pl.tlewandster.ffwork.pricing.StandardPricing;
import pl.tlewandster.ffwork.repo.*;
import pl.tlewandster.ffwork.service.BookingService;

import java.util.ArrayList;

public class Tests {
    static void main() {
        UserRepository users = new InMemoryUserRepository(new ArrayList<>());
        ResourceRepository resources = new InMemoryResourceRepository(new ArrayList<>());
        BookingRepository bookings = new InMemoryBookingRepository(new ArrayList<>());
        PricingPolicy pricingPolicy = new StandardPricing();
        BookingService service = new BookingService(users, resources, bookings, pricingPolicy);

        // Test 0 — Dane startowe

        // ADD_ROOM "Sala Alfa" 12 80
        resources.add(new Room("Sala Alfa", 12, 80));

        // ADD_DESK "Hot-1" hot 25
        resources.add(new Desk("Hot-1", "hot", 25));

        // ADD_DEVICE "Projektor-1" 2 40
        resources.add((new Device("Projector-1", 2, 40)));

        // ADD_USER INDIVIDUAL anna@ex.com "Anna Nowak"
        users.add(new IndividualUser("anna@ex.com", "Anna Nowak"));

        // ADD_USER COMPANY biuro@acme.pl "ACME Sp. z o.o." 5211234567
        users.add(new CompanyUser("biuro@acme.pl", "ACME Sp. z o.o.", "5211234567"));

        // SET_PRICING STANDARD
        pricingPolicy = new StandardPricing();

        // LIST_RESOURCES
        System.out.println("LISTA ZASOBÓW:\n");
        resources.findAll().forEach(resource -> System.out.println(resource.describe()));
        System.out.println("-".repeat(20));

        // LIST_USERS
        System.out.println("LISTA UŻYTKOWNIKÓW:\n");
        users.findAll().forEach(System.out::println);
        System.out.println("-".repeat(20));

        // Test 1 — Rezerwacja i płatność (overloading)

        // BOOK biuro@acme.pl "Sala Alfa" 2025-09-15T10:00 2025-09-15T12:00 → PENDING, cena 160.00 PLN.
        Booking book1 = service.book("biuro@acme.pl", "Sala Alfa", "2025-09-15T10:00", "2025-09-15T12:00");
        System.out.println(book1);
        System.out.println("-".repeat(20));

        // CONFIRM <id>
        service.confirm(book1.getId());
        System.out.println(book1);
        System.out.println("-".repeat(20));

        // PAY <id> CARD 4242
        CardPayment cardPayment1 = new CardPayment(book1.getId(), book1.getCalculatedPrice(), "4242");
        System.out.println(cardPayment1);
        cardPayment1.capture();
        System.out.println(cardPayment1);
        System.out.println("-".repeat(20));

        // INVOICE <id>
        // TODO Czegoś nie rozumiem

        // BOOK biuro@acme.pl "Sala Alfa" 2025-09-16T09:00 90
        Booking book2 = service.book("biuro@acme.pl", "Sala Alfa", "2025-09-16T09:00", 90);
        System.out.println(book2);
        System.out.println("-".repeat(20));

        // Test 2 — Kolizje
        // BOOK biuro@acme.pl "Sala Alfa" 2025-09-15T11:00 2025-09-15T13:00
        try {
            Booking book3 = service.book("biuro@acme.pl", "Sala Alfa", "2025-09-15T11:00", "2025-09-15T13:00");
        } catch (Exception e) {
            System.out.println(e);;
        }
        Booking book4 = service.book("biuro@acme.pl", "Hot-1", "2025-09-15T11:00", "2025-09-15T13:00");
        System.out.println(book4);
        System.out.println("-".repeat(20));


    }
}
