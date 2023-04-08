package com.jpmc.theater.service;

import com.jpmc.theater.exception.ReservationException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;

/**
 * IReservationService - declares the functionality provided by the ReservationService
 * Currently only supports making reservations. In the future, it could be expanded to include reading, updating, and
 * cancelling reservations
 */
public interface IReservationService {
    /**
     * makeReservation - provides functionality for making reservations for a movie showing
     * @param customer - The customer associated with the reservation.
     * @param showing - The movie showing for to make the reservation.
     * @param ticketCount - The number of tickets in the reservation.
     * @return A new Reservation instance if the reservation was created.
     * @throws ReservationException if the reservation could not be created.
     */
    Reservation makeReservation(Customer customer, Showing showing, int ticketCount) throws ReservationException;
}
