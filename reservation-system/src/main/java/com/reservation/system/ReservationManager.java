package com.reservation.system;
import java.util.*;
/**
 * @author premraj.murugaraj
 */
public class ReservationManager {
    private Map<String, Reservation> reservations = new HashMap<>();

    // Add a reservation
    public void addReservation(String reservationId, String customerName, Date reservationDate) {
        reservations.put(reservationId, new Reservation(reservationId, customerName, reservationDate));
    }

    // Cancel a reservation
    public boolean cancelReservation(String reservationId) {
        if (reservations.containsKey(reservationId)) {
            reservations.remove(reservationId);
            return true;
        }
        return false;
    }

    // View all reservations
    public void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations available.");
        } else {
            for (Reservation reservation : reservations.values()) {
                System.out.println(reservation);
            }
        }
    }

    // Search reservation by customer name
    public Reservation searchReservationByCustomer(String customerName) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.customerName().equalsIgnoreCase(customerName)) {
                return reservation;
            }
        }
        return null;
    }
}
