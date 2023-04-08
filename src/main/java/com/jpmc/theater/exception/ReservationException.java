package com.jpmc.theater.exception;

/**
 * ReservationException E a checked exception that is thrown if an error occurs while creating a reservation.
 */
public class ReservationException extends RuntimeException {

    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(Throwable t) {
        super(t);
    }
}
