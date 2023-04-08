package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {
    private static final String TEST_MOVIE_TITLE = "Spider-Man: No Way Home";
    private static final double TEST_TICKET_PRICE = 12.5;
    private static final LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 6);
    private static final LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);
    private static final LocalDate DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 7);
    private static final LocalTime DISCOUNT_TIME = LocalTime.of(12, 30);
    private static final int NON_DISCOUNT_SEQUENCE = 5;
    private PricingService pricingService;

    @BeforeEach
    public void setup() {
        pricingService = new PricingService();
    }

    @Test
    public void testCalculateTicketPriceWithNoDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 9, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be full price
        assertEquals(TEST_TICKET_PRICE, pricingService.calculateTicketPrice(showing));
    }

    @Test
    public void testCalculateTicketPriceIsNotLessThanZero() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), 1, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be full price
        assertEquals(0, pricingService.calculateTicketPrice(showing));
    }

    @Test
    public void testCalculateDiscountForFirstShow() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(3, pricingService.calculateDiscount(showing));
    }

    @Test
    public void testCalculateDiscountForNoDiscountConditions() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        for (int sequence = 3; sequence <= 9; sequence++) {
            Showing showing = new Showing(spiderMan, sequence, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
            // discount should be 0.0
            assertEquals(0.0, pricingService.calculateDiscount(showing));
        }
    }

    @Test
    public void testCalculateDiscountForAllDiscountConditions() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(DISCOUNT_DATE, DISCOUNT_TIME));
        // discount should be 25% of ticket price which is the largest discount available
        assertEquals(PricingService.TIME_DISCOUNT_PERCENT * TEST_TICKET_PRICE, pricingService.calculateDiscount(showing));
    }

    @Test
    public void testCalculateDiscountForSecondShow() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $2.00 off of the regular price of 12.5
        assertEquals(2.0, pricingService.calculateDiscount(showing));
    }

    @Test
    public void testCalculateSequenceDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);

        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $3.00 off of the regular price of 12.5
        assertEquals(3.0, pricingService.calculateSequenceDiscount(showing));
        showing = new Showing(spiderMan, 2, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be $2.00 off of the regular price of 12.5
        assertEquals(2.0, pricingService.calculateSequenceDiscount(showing));

        // test the non-discount sequence values
        for (int sequence = 3; sequence <= 9; sequence++) {
            showing = new Showing(spiderMan, sequence, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
            // discount should be 0.0
            assertEquals(0.0, pricingService.calculateDiscount(showing));
        }
    }

    /**
     * testSpecialMovieDiscount - test 20% discount for special movies
     */
    @Test
    void testSpecialMovieDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 1);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(0.2 * TEST_TICKET_PRICE, pricingService.calculateDiscount(showing));
    }

    /**
     * testSpecialMovieDiscount - test ticket price is 80% of regular ticket price
     */
    @Test
    void testSpecialMoviePrice() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 1);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(0.8 * TEST_TICKET_PRICE, pricingService.calculateTicketPrice(showing));
    }

    /**
     * testDateDiscount - test 1.00 discount for showings that meet the date discount criteria (7th of the month).
     */
    @Test
    void testDateDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // discount should be $1.00
        assertEquals(1.0, pricingService.calculateDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // discount should be 0.0
        assertEquals(0.0, pricingService.calculateDiscount(showing));
    }

    /**
     * testCalculateTimeDiscount - tests that a 25% discount is applied when the movie showing falls between the
     * time discount start and end times
     */
    @Test
    public void testCalculateTimeDiscount() {
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, DISCOUNT_TIME));
        // discount should be 0.0
        assertEquals(0.25 * TEST_TICKET_PRICE, pricingService.calculateTimeDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_START_HOUR, 0)));
        // discount should be 0.0
        assertEquals(0.25 * TEST_TICKET_PRICE, pricingService.calculateTimeDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_END_HOUR, 0)));
        // discount should be 0.0
        assertEquals(0.25 * TEST_TICKET_PRICE, pricingService.calculateTimeDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_START_HOUR - 1, 0)));
        // discount should be 0.0
        assertEquals(0.0, pricingService.calculateTimeDiscount(showing));

        showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, LocalTime.of(PricingService.TIME_DISCOUNT_END_HOUR + 1, 0)));
        // discount should be 0.0
        assertEquals(0.0, pricingService.calculateTimeDiscount(showing));
    }
}
