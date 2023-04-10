package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IPricingTest;
import com.jpmc.theater.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeOfDayiscountTest implements IPricingTest {

    TimeOfDayDiscount timeOfDayDiscount;

    @BeforeEach
    public void setup() {
        timeOfDayDiscount = new TimeOfDayDiscount();
    }

    /**
     * testCalculateTimeDiscount - tests that a 25% discount is applied when the movie showing falls between the
     * time discount start and end times
     */
    @Test
    public void testCalculateTimeDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, DISCOUNT_TIME));
        // discount should be 25% of ticket price
        assertEquals(0.25 * TEST_TICKET_PRICE, timeOfDayDiscount.calculateDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_START_HOUR, 0)));
        // discount should be 25% of ticket price
        assertEquals(0.25 * TEST_TICKET_PRICE, timeOfDayDiscount.calculateDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_END_HOUR, 0)));
        // discount should be 25% of ticket price
        assertEquals(0.25 * TEST_TICKET_PRICE, timeOfDayDiscount.calculateDiscount(showing));
    }

    /**
     * testCalculateTimeDiscount - tests that a 25% discount is applied when the movie showing falls between the
     * time discount start and end times
     */
    @Test
    public void testCalculateTimeDiscountIsNotAppliedOutsideOfDiscountPeriod() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, DISCOUNT_TIME));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_START_HOUR - 1, 0)));
        // discount should be 0.0
        assertEquals(0.0, timeOfDayDiscount.calculateDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_END_HOUR + 1, 0)));
        // discount should be 0.0
        assertEquals(0.0, timeOfDayDiscount.calculateDiscount(showing));
    }
}