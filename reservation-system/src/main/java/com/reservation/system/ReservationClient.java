package com.reservation.system;
import java.util.*;
/**
 * @author premraj.murugaraj
 */
public class ReservationClient {

    private static ReservationOperation ops = new ReservationOperation();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Reservation System =====");
            System.out.println("1. Make a Reservation");
            System.out.println("2. View All Reservations");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. Search Reservation");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    ops.makeReservation();
                    break;
                case 2:
                    ops.viewAllReservations();
                    break;
                case 3:
                    ops.cancelReservation();
                    break;
                case 4:
                    ops.searchReservation();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
