package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IPricingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SequenceDiscountTest - tests the SequenceDiscount class.
 */
class SequenceDiscountTest implements IPricingTest {

    SequenceDiscount sequenceDiscount;

    @BeforeEach
    public void setup() {
        sequenceDiscount = new SequenceDiscount();
    }

    /**
     * testDateDiscount - test 1.00 discount for showings that meet the date discount criteria (7th of the month).
     */
    @Test
    public void testCalculateDiscountForFirstShow() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(3, sequenceDiscount.calculateDiscount(showing));
    }
    @Test
    public void testCalculateDiscountForSecondShow() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $2.00 off of the regular price of 12.5
        assertEquals(2.0, sequenceDiscount.calculateDiscount(showing));
    }

    @Test
    public void testCalculateSequenceDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);

        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $3.00 off of the regular price of 12.5
        assertEquals(3.0, sequenceDiscount.calculateDiscount(showing));
        showing = new Showing(spiderMan, 2, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $2.00 off of the regular price of 12.5
        assertEquals(2.0, sequenceDiscount.calculateDiscount(showing));

        // test the non-discount sequence values
        for (int sequence = 3; sequence <= 9; sequence++) {
            showing = new Showing(spiderMan, sequence, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
            // discount should be 0.0
            assertEquals(0.0, sequenceDiscount.calculateDiscount(showing));
        }
    }
}