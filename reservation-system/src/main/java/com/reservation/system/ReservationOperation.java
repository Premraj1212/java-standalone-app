package com.reservation.system;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

/**
 * @author premraj.murugaraj
 */
public class ReservationOperation {

    private static ReservationManager reservationManager = new ReservationManager();

    private static Scanner scanner = new Scanner(System.in);

    public void makeReservation() {
        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Reservation Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            reservationManager.addReservation(reservationId, customerName, date);
            System.out.println("Reservation made successfully!");
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    public void cancelReservation() {
        System.out.print("Enter Reservation ID to cancel: ");
        String reservationId = scanner.nextLine();
        if (reservationManager.cancelReservation(reservationId)) {
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void searchReservation() {
        System.out.print("Enter Customer Name to search: ");
        String customerName = scanner.nextLine();
        Reservation reservation = reservationManager.searchReservationByCustomer(customerName);
        if (reservation != null) {
            System.out.println("Reservation found: " + reservation);
        } else {
            System.out.println("No reservation found for this customer.");
        }
    }

    public void viewAllReservations(){
        reservationManager.viewAllReservations();
    }
}
