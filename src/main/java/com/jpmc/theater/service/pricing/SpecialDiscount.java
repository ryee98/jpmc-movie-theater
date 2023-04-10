package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

public class SpecialDiscount implements IPricingDiscount {
    private static int MOVIE_CODE_SPECIAL = 1;
    public static final double SPECIAL_MOVIE_DISCOUNT_PERCENT = 0.2; // 20% discount for special movie

    @Override
    public double calculateDiscount(Showing showing) {
        double specialDiscount = 0;
        if (isSpecialMovie(showing.getMovie())) {
            specialDiscount = showing.getMovie().getTicketPrice() * SPECIAL_MOVIE_DISCOUNT_PERCENT;
        }
        return specialDiscount;
    }

    /**
     * isSpecialMovie - A utility method that returns whether the movie is a special movie.
     * @param movie - The movie to check
     * @return <code>true</code> if the movie is a special movie, <code>false</code> otherwise.
     */
    public boolean isSpecialMovie(Movie movie) {
        return MOVIE_CODE_SPECIAL == movie.getSpecialCode();
    }
}
