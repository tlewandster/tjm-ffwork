package pl.tlewandster.ffwork;

import pl.tlewandster.ffwork.domain.IndividualUser;
import pl.tlewandster.ffwork.pricing.PricingPolicy;
import pl.tlewandster.ffwork.pricing.StandardPricing;
import pl.tlewandster.ffwork.repo.*;
import pl.tlewandster.ffwork.service.BookingService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final UserRepository users = new InMemoryUserRepository(new ArrayList<>());
    static final ResourceRepository resources = new InMemoryResourceRepository(new ArrayList<>());
    static final BookingRepository bookings = new InMemoryBookingRepository(new ArrayList<>());
    static final PricingPolicy pricingPolicy = new StandardPricing();
    static final BookingService service = new BookingService(users, resources, bookings, pricingPolicy);
    static final Scanner scanner = new Scanner(System.in);

    public static void main() {



        boolean isRunning = true;

        while (isRunning) {
            showHelp();
            System.out.println(">>>");
            int command = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (command) {
                    case 0 -> isRunning = false;
                    case 1 -> handleAddUserIndividual();
                    default -> System.out.println("Błędna komenda");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("\u001B[31m" + "BŁĄD: " + e.getMessage() + "\u001B[0m");
                System.out.println();
            }

        }
        scanner.close();
    }

    private static void handleAddUserIndividual() {
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Imię i nazwisko:");
        String fullName = scanner.nextLine();
        users.add(new IndividualUser(email, fullName));
    }

    private static void showHelp() {
        System.out.println("""
                KOMENDY CLI
                -----------
                Użytkownicy:
                  1 - ADD_USER INDIVIDUAL <email> <fullName>
                  2 - ADD_USER COMPANY <email> <companyName> <nip>
                  3 - LIST_USERS
                Zasoby:
                  4 - ADD_ROOM <name> <seats> <hourlyRate>
                  5 - ADD_DESK <name> <hot|fixed> <hourlyRate>
                  6 - ADD_DEVICE <name> <quantity> <hourlyRate>
                  7 - LIST_RESOURCES [TYPE=<ROOM|DESK|DEVICE>]
                Rezerwacje:
                  8 - BOOK <userEmail> <resourceName> <startIso> <endIso|durationMinutes>
                  9 - CONFIRM <bookingId>
                  10 - CANCEL <bookingId>
                  11 - LIST_BOOKINGS [USER=<email>] [RESOURCE=<name>] [STATUS=<PENDING|CONFIRMED|CANCELLED|COMPLETED>]
                Polityki cen:
                  12 - SET_PRICING STANDARD|HAPPY_HOURS
                Płatności / Faktury:
                  13 - PAY <bookingId> CARD <last4>
                  14 - PAY <bookingId> WALLET
                  15 - INVOICE <bookingId>
                Wyjście:
                  0 - QUIT
                """);
    }
}
