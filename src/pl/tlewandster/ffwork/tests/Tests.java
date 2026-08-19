package pl.tlewandster.ffwork.tests;

import pl.tlewandster.ffwork.domain.*;
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
        new BookingService(users, resources, bookings, pricingPolicy);

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

    }
}
