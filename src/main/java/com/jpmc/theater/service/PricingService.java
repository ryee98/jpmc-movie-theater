package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

/**
 * PricingService - service class that implements pricing rules and provides methods for
 * calculating discounts and ticket prices.
 *
 * Created by ryee on 4/6/23
 */
@Service
public class PricingService {
    private static int MOVIE_CODE_SPECIAL = 1;
    public static final double SPECIAL_MOVIE_DISCOUNT_PERCENT = 0.2; // 20% discount for special movie
    public static final double TIME_DISCOUNT_PERCENT = 0.25; // 25% discount for movies starting between certain times
    public static final double FIRST_SHOW_DISCOUNT = 3.00;
    public static final double SECOND_SHOW_DISCOUNT = 2.00;

    public static final int TIME_DISCOUNT_START_HOUR = 11; // 11 is 11:00am
    public static final int TIME_DISCOUNT_END_HOUR = 16;   // 16 is 4:00pm

    public static final int DATE_DISCOUNT_DAY = 7; // 7th of the month is discount day
    public static final double DATE_DISCOUNT_AMOUNT = 1.00;

    public double calculateTicketPrice(Showing showing, Movie movie) {
        return Math.max(movie.getTicketPrice() - calculateDiscount(showing, movie), 0.0); // prevent negative ticket pricing
    }

    /**
     * calculateDiscount - calculates the discount off of the ticket price based on movie sequence or special status of movie.
     * Value can be 0.0 if no discount rules apply.
     *
     * @param showing - the movie showing
     * @param movie - the movie
     * @return 0.0 if no discounts apply, otherwise greater of the special discount or the sequence discount
     */
    public double calculateDiscount(Showing showing, Movie movie) {

        // calculate special discount
        double specialDiscount = 0;
        if (isSpecialMovie(movie)) {
            specialDiscount = movie.getTicketPrice() * SPECIAL_MOVIE_DISCOUNT_PERCENT;
        }

        // calculate sequence discount
        double sequenceDiscount = calculateSequenceDiscount(showing);

        // calculate time-based discount
        double timeDiscount = calculateTimeDiscount(showing, movie);

        // calculate date-based discount
        double dateDiscount = calculateDateDiscount(showing);

        return Collections.max(Arrays.asList(specialDiscount, sequenceDiscount, timeDiscount, dateDiscount));
    }

    /**
     * calculateSequenceDiscount - calculates the amount of discount based on the showing sequence;
     * @param showing - The movie showing
     * @return The discount amount or 0.0 if there is no discount
     */
    protected double calculateSequenceDiscount(Showing showing) {
        double discount = 0.0;
        int showSequence = showing.getSequenceOfTheDay();

        if (showing.getSequenceOfTheDay() == 1) {
            discount = FIRST_SHOW_DISCOUNT;
        } else if (showSequence == 2) {
            discount = SECOND_SHOW_DISCOUNT;
        }
        return discount;
    }

    /**
     * calculateTimeDiscount - calculates the discount amount based on the time of the movie showing
     * @param showing - The movie showing
     * @param movie - The movie
     * @return The discount amount or 0.0 if there is no discount
     */
    protected double calculateTimeDiscount(Showing showing, Movie movie) {
        double discount = 0.0;
        if (showing.getStartTime().getHour() >= TIME_DISCOUNT_START_HOUR && showing.getStartTime().getHour() <= TIME_DISCOUNT_END_HOUR) {
            discount = movie.getTicketPrice() * TIME_DISCOUNT_PERCENT;
        }
        return discount;
    }

    /**
     * calculateDateDiscount - calculates the discount amount (if any) based on the date of the showing
     * @param showing - The showing information
     * @return The discount amount or 0.0 if there is no discount
     */
    protected double calculateDateDiscount(Showing showing) {
        double discount = 0.0;
        if (showing.getStartTime().getDayOfMonth() == DATE_DISCOUNT_DAY) {
            discount = DATE_DISCOUNT_AMOUNT;
        }
        return discount;
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
