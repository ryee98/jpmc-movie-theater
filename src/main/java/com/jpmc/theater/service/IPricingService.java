package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

public interface IPricingService {
    /**
     * calculateTicketPrice - interface method for calculating a ticket price given a showing and movie
     * @param showing - the movie showing
     * @return - the cost of the ticket
     */
    double calculateTicketPrice(Showing showing);

    /**
     * calculateDiscount - interface method for calculating a ticket discount based on a showing and movie.
     * @param showing
     * @return
     */
    double calculateDiscount(Showing showing);
}
