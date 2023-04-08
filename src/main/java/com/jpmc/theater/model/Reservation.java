package com.jpmc.theater.model;

import lombok.Data;

/**
 * Reservation - a model class that represents a movie reservation.
 */
@Data
public class Reservation {
    private Customer customer;
    private Showing showing;
    // the number of persons in the reservation
    private int partyCount;

    public Reservation(Customer customer, Showing showing, int partyCount) {
        this.customer = customer;
        this.showing = showing;
        this.partyCount = partyCount;
    }
}
