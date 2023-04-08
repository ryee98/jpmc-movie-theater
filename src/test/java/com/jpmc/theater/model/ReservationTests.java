package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    public void testConstructor() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        Reservation reservation = new Reservation(customer, showing, 4);
        assertEquals(customer, reservation.getCustomer());
        assertEquals(showing, reservation.getShowing());
        assertEquals(4, reservation.getPartyCount());
    }
}
