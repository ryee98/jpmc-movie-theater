package com.jpmc.theater.service;

import com.jpmc.theater.exception.ReservationException;
import com.jpmc.theater.exception.ScheduleException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryee on 4/7/23
 */
@Service
public class ReservationService {

    private ScheduleService scheduleService;

    private PricingService pricingService;

    @Autowired
    public ReservationService(ScheduleService scheduleService, PricingService pricingService) {
        this.scheduleService = scheduleService;
        this.pricingService = pricingService;
    }

    public Reservation makeReservation(Customer customer, int sequence, int ticketCount) {
        // perform some validations. Ideally, this could be done using annotations from the Validator framework
        if (customer == null ) {
            throw new ReservationException("customer is required");
        }
        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new ReservationException("customer name is required");
        }
        if (ticketCount <= 0) {
            throw new ReservationException("invalid ticket count");
        }

        Showing showing;
        try {
            showing = scheduleService.getShowing(sequence);
        } catch (ScheduleException se) {
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, ticketCount);
    }

    /**
     * calculateFee - calculates the total fee for a reservation by obtaining the ticket price and multiplying it
     * by the number of persons in the reservation.
     * @param reservation - A reservation containing a Showing and number in the party
     * @return The calculated total fee for the reservation
     */
    public double calculateFee(Reservation reservation) {
        double ticketPrice =  pricingService.calculateTicketPrice(reservation.getShowing(), reservation.getShowing().getMovie());
        return reservation.getPartyCount() * ticketPrice;
    }
}
