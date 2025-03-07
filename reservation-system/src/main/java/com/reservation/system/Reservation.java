package com.reservation.system;

import java.util.Date;

/**
 * @author premraj.murugaraj
 */
public record Reservation(String reservationId,String customerName, Date reservationDate) {
}
