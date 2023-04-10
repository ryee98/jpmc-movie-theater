package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IPricingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialDiscountTest implements IPricingTest {

    SpecialDiscount specialDiscount;

    @BeforeEach
    public void setup() {
        specialDiscount = new SpecialDiscount();
    }


    /**
     * testSpecialMovieDiscount - test 20% discount for special movies
     */
    @Test
    void testSpecialMovieDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 1);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(0.2 * TEST_TICKET_PRICE, specialDiscount.calculateDiscount(showing));
    }


}