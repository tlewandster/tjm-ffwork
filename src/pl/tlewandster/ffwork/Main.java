package pl.tlewandster.ffwork;

import pl.tlewandster.ffwork.billing.Invoice;
import pl.tlewandster.ffwork.domain.*;
import pl.tlewandster.ffwork.payment.CardPayment;
import pl.tlewandster.ffwork.payment.Payment;
import pl.tlewandster.ffwork.pricing.HappyHoursPricing;
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
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main() {


        boolean isRunning = true;

        while (isRunning) {
            showHelp();
            System.out.print(">>> ");
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
                    case 11 -> handleCancel();
                    case 12 -> handleListBookings();
                    case 13 -> handleSetPricing();
                    case 14 -> handlePay();
                    case 15 -> handleInvoice();
                    default -> printError("Błędna komenda");
                }
            } catch (Exception e) {
                printError(e.getMessage());
            }
        }
        printAck("Dziękujemy za skorzystanie z tego świetnego, dopracowanego, nie mającego sobie równych programu. Bye.");
        scanner.close();
    }

    private static void handleInvoice() {
        System.out.println("WYSTAW FAKTURĘ");
        System.out.print("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        Booking book = bookings.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Rezerwacja nie znaleziona"));
        Invoice invoice = new Invoice();
        invoice.toInvoice(book);
        printAck("Zafakturowano");
    }

    private static void handlePay() {
        System.out.println("DOKONAJ PŁATNOŚCI");
        System.out.print("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        System.out.print("Cztery ostatnie numery karty: ");
        String last4 = scanner.nextLine();
        Booking book = bookings.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Rezerwacja nie znaleziona"));
        Payment payment = new CardPayment(book.getId(), book.getCalculatedPrice(), last4);
        printAck("Zapłacono. Oczekiwanie na potwierdzenie płatności...");
        payment.capture();
        printAck("Płatność potwierdzona");
    }

    private static void handleSetPricing() {
        System.out.println("USTAW POLITYKĘ CEN");
        System.out.print("S - Standard / H - Happy Hours: ");
        String pricingPolicy = scanner.nextLine();
        if (pricingPolicy.equalsIgnoreCase("s")) {
            service.setPricingPolicy(new StandardPricing());
        } else if (pricingPolicy.equalsIgnoreCase("h")) {
            service.setPricingPolicy(new HappyHoursPricing());
        } else {
            throw new IllegalArgumentException("An invalid value was entered");
        }
        printAck("Zmieniono politykę cen");
    }

    private static void handleListBookings() {
        System.out.println("LISTA REZERWACJI:\n");
        bookings.findAll().forEach(System.out::println);
    }

    private static void handleCancel() {
        System.out.println("POTWIERDŹ REZERWACJĘ");
        System.out.print("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        service.cancel(bookingId);
        printAck("Rezerwacja " + bookingId + " anulowana");
    }

    private static void handleConfirm() {
        System.out.println("POTWIERDŹ REZERWACJĘ");
        System.out.print("Numer rezerwacji: ");
        String bookingId = scanner.nextLine();
        service.confirm(bookingId);
        printAck("Rezerwacja " + bookingId + " potwierdzona");
    }

    private static void handleBookStartDuration() {
        System.out.println("ZRÓB REZERWACJĘ");
        System.out.print("Email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Nazwa zasobu: ");
        String resourceName = scanner.nextLine();
        System.out.print("Od (rrrr-mm-ddThh:mm): ");
        String startIso = scanner.nextLine();
        System.out.print("Okres czasu w minutach: ");
        int durationMinutes = scanner.nextInt();
        Booking book = service.book(userEmail, resourceName, startIso, durationMinutes);
        printAck("Dodano rezerwację:\n" + book);
    }

    private static void handleBookStartEnd() {
        System.out.println("ZRÓB REZERWACJĘ");
        System.out.print("Email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Nazwa zasobu: ");
        String resourceName = scanner.nextLine();
        System.out.print("Od (rrrr-mm-ddThh:mm): ");
        String startIso = scanner.nextLine();
        System.out.print("Do (rrrr-mm-ddThh:mm): ");
        String endIso = scanner.nextLine();
        Booking book = service.book(userEmail, resourceName, startIso, endIso);
        printAck("Dodano rezerwację:\n" + book);
    }

    private static void handlListResources() {
        System.out.println("LISTA ZASOBÓW:\n");
        resources.findAll().forEach(resource -> System.out.println(resource.describe()));
    }

    private static void nandleAddDevice() {
        System.out.println("DODAJ URZĄDZENIE");
        System.out.print("Nazwa urządzenia: ");
        String name = scanner.nextLine();
        System.out.print("Ilość: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        Device device = new Device(name, quantity, customHourlyRate);
        resources.add(device);
        printAck("Dodano nowe urządzenie:\n" + device);
    }

    private static void handleAddDesk() {
        System.out.println("DODAJ STANOWISKO");
        System.out.print("Nazwa stanowiska: ");
        String name = scanner.nextLine();
        System.out.print("Typ: ");
        String type = scanner.nextLine();
        System.out.print("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        Desk desk = new Desk(name, type, customHourlyRate);
        resources.add(desk);
        printAck("Dodano nowe stanowisko:\n" + desk);
    }

    private static void handleAddRoom() {
        System.out.println("DODAJ SALĘ");
        System.out.print("Nazwa sali: ");
        String name = scanner.nextLine();
        System.out.print("Ilość miejsc: ");
        int seats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cena za godz.: ");
        int customHourlyRate = scanner.nextInt();
        scanner.nextLine();
        Room room = new Room(name, seats, customHourlyRate);
        resources.add(room);
        printAck("Dodano nową salę:\n" + room);
    }

    private static void handleListUsers() {
        System.out.println("LISTA UŻYTKOWNIKÓW:\n");
        users.findAll().forEach(System.out::println);
    }

    private static void handleAddCompanyUser() {
        System.out.println("DODAJ FIRMĘ");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nazwa firmy: ");
        String companyName = scanner.nextLine();
        System.out.print("NIP: ");
        String taxId = scanner.nextLine();
        CompanyUser companyUser = new CompanyUser(email, companyName, taxId);
        users.add(companyUser);
        printAck("Dodano nową firmę:\n" + companyUser);
    }

    private static void handleAddIndividualUser() {
        System.out.println("DODAJ UŻYTKOWNIKA");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Imię i nazwisko: ");
        String fullName = scanner.nextLine();
        IndividualUser individualUser = new IndividualUser(email, fullName);
        users.add(individualUser);
        printAck("Dodano nowego użytkownika indywidualnego:\n" + individualUser);

    }

    private static void printError(String text) {
        System.out.println();
        System.out.println(RED + "BŁĄD: " + text + RESET);
        System.out.println();
    }

    private static void printAck(String text) {
        System.out.println();
        System.out.println(GREEN + "OK: " + text + RESET);
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
                  8 - BOOK <userEmail> <resourceName> <startIso> <endIso>>
                  9 - BOOK <userEmail> <resourceName> <startIso> <durationMinutes>
                  10 - CONFIRM <bookingId>
                  11 - CANCEL <bookingId>
                  12 - LIST_BOOKINGS [USER=<email>] [RESOURCE=<name>] [STATUS=<PENDING|CONFIRMED|CANCELLED|COMPLETED>]
                Polityki cen:
                  13 - SET_PRICING STANDARD|HAPPY_HOURS
                Płatności / Faktury:
                  14 - PAY <bookingId> CARD <last4>
                  15 - INVOICE <bookingId>
                Wyjście:
                  0 - QUIT
                """);
    }
}
