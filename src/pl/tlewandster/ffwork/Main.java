package pl.tlewandster.ffwork;

import pl.tlewandster.ffwork.domain.*;
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
                    case 1 -> handleAddIndividualUser();
                    case 2 -> handleAddCompanyUser();
                    case 3 -> handleListUsers();
                    case 4 -> handleAddRoom();
                    case 5 -> handleAddDesk();
                    case 6 -> nandleAddDevice();
                    case 7 -> handlListResources();
                    case 8 -> handleBookStartEnd();
                    case 9 -> handleBookStartDuration();
                    case 10 -> handleConfirm();
                    case 11 -> handleCansel();
                    default -> printError("Błędna komenda");
                }
            } catch (Exception e) {
                printError(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void handleCansel() {
        System.out.println("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        service.cancel(bookingId);
    }

    private static void handleConfirm() {
        System.out.println("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        service.confirm(bookingId);
    }

    private static void handleBookStartDuration() {
        System.out.println("Email: ");
        String userEmail = scanner.nextLine();
        System.out.println("Nazwa zasobu: ");
        String resourceName = scanner.nextLine();
        System.out.println("Od (rrrr-mm-ddThh:mm): ");
        String startIso = scanner.nextLine();
        System.out.println("Okres czasu w minutach: ");
        int durationMinutes = scanner.nextInt();
        service.book(userEmail, resourceName, startIso, durationMinutes);
    }

    private static void handleBookStartEnd() {
        System.out.println("Email: ");
        String userEmail = scanner.nextLine();
        System.out.println("Nazwa zasobu: ");
        String resourceName = scanner.nextLine();
        System.out.println("Od (rrrr-mm-ddThh:mm): ");
        String startIso = scanner.nextLine();
        System.out.println("Do (rrrr-mm-ddThh:mm): ");
        String endIso = scanner.nextLine();
        service.book(userEmail, resourceName, startIso, endIso);
    }

    private static void handlListResources() {
        System.out.println("LISTA ZASOBÓW:\n");
        resources.findAll().forEach(resource -> System.out.println(resource.describe()));
        System.out.println("-".repeat(20));
    }

    private static void nandleAddDevice() {
        System.out.println("Nazwa urządzenia: ");
        String name = scanner.nextLine();
        System.out.println("Ilość: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        resources.add((new Device(name, quantity, customHourlyRate)));
    }

    private static void handleAddDesk() {
        System.out.println("Nazwa biurka: ");
        String name = scanner.nextLine();
        System.out.println("Typ: ");
        String type = scanner.nextLine();
        System.out.println("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        resources.add(new Desk(name, type, customHourlyRate));
    }

    private static void handleAddRoom() {
        System.out.println("Nazwa sali: ");
        String name = scanner.nextLine();
        System.out.println("Ilość miejsc: ");
        int seats = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        resources.add(new Room(name, seats, customHourlyRate));
    }

    private static void handleListUsers() {
        System.out.println("LISTA UŻYTKOWNIKÓW:\n");
        users.findAll().forEach(System.out::println);
        System.out.println("-".repeat(20));
    }

    private static void handleAddCompanyUser() {
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Nazwa firmy:");
        String companyName = scanner.nextLine();
        System.out.println("NIP:");
        String taxId = scanner.nextLine();
        users.add(new CompanyUser(email, companyName, taxId));
    }

    private static void handleAddIndividualUser() {
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Imię i nazwisko:");
        String fullName = scanner.nextLine();
        users.add(new IndividualUser(email, fullName));
    }

    private static void printError(String text) {
        System.out.println();
        System.out.println("\u001B[31m" + "BŁĄD: " + text + "\u001B[0m");
        System.out.println();
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
