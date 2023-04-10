package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IPricingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DateDiscountTest implements IPricingTest {

    DateDiscount dateDiscount;

    @BeforeEach
    public void setup() {
        dateDiscount = new DateDiscount();
    }

    /**
     * testDateDiscount - test 1.00 discount for showings that meet the date discount criteria (7th of the month).
     */
    @Test
    public void testDateDiscountDate() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // discount should be $1.00
        assertEquals(1.0, dateDiscount.calculateDiscount(showing));
    }

    @Test
    public void testDateDiscountOnNonDiscountDate() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(DISCOUNT_DATE, NON_DISCOUNT_TIME));
        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // discount should be 0.0
        assertEquals(0.0, dateDiscount.calculateDiscount(showing));
    }
}