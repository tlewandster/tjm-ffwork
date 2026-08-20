package pl.tlewandster.ffwork;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            showHelp();
            System.out.println(">>>");
            int command = scanner.nextInt();
            switch (command) {
                case 0 -> isRunning = false;
                case 1 -> handleAddUserIndividual();
                default -> System.out.println("Błędna komenda");
            }

        }

    }

    private static void handleAddUserIndividual() {
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
