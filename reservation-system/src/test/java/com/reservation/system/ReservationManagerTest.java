package com.reservation.system;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author premraj.murugaraj
 */
public class ReservationManagerTest {
    private ReservationManager reservationManager;
    @BeforeEach
    public void setUp() {
        reservationManager = new ReservationManager();
    }

    @Test
    public void testAddReservation() {
        String reservationId = "123";
        String customerName = "Sadaya Varman";
        Date reservationDate = new Date();

        reservationManager.addReservation(reservationId, customerName, reservationDate);

        // Verify the reservation has been added
        Reservation reservation = reservationManager.searchReservationByCustomer(customerName);
        assertNotNull(reservation);
        assertEquals(reservationId, reservation.reservationId());
        assertEquals(customerName, reservation.customerName());
    }

    @Test
    public void testCancelReservation() {
        String reservationId = "123";
        String customerName = "Sadaya Varman";
        Date reservationDate = new Date();

        reservationManager.addReservation(reservationId, customerName, reservationDate);

        // Verify cancellation
        boolean result = reservationManager.cancelReservation(reservationId);
        assertTrue(result);

        // Verify cancellation by checking if the reservation no longer exists
        Reservation reservation = reservationManager.searchReservationByCustomer(customerName);
        assertNull(reservation);
    }

    @Test
    public void testCancelNonExistentReservation() {
        boolean result = reservationManager.cancelReservation("non-existent-id");
        assertFalse(result);
    }

    @Test
    public void testViewAllReservations() {
        String reservationId1 = "123";
        String customerName1 = "Sembavalm Pandian";
        Date reservationDate1 = new Date();

        String reservationId2 = "456";
        String customerName2 = "Senguttuvan Pandian";
        Date reservationDate2 = new Date();

        reservationManager.addReservation(reservationId1, customerName1, reservationDate1);
        reservationManager.addReservation(reservationId2, customerName2, reservationDate2);

        // Test viewing all reservations
        reservationManager.viewAllReservations(); // You may want to mock System.out or capture output
    }

    @Test
    public void testSearchReservationByCustomer() {
        String reservationId = "123";
        String customerName = "Rana Dheeran";
        Date reservationDate = new Date();

        reservationManager.addReservation(reservationId, customerName, reservationDate);

        Reservation reservation = reservationManager.searchReservationByCustomer(customerName);
        assertNotNull(reservation);
        assertEquals(customerName, reservation.customerName());
    }

    @Test
    public void testSearchNonExistentReservation() {
        Reservation reservation = reservationManager.searchReservationByCustomer("non-existent-customer");
        assertNull(reservation);
    }


}
