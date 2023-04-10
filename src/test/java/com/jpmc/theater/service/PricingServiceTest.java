package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.service.pricing.DateDiscount;
import com.jpmc.theater.service.pricing.IPricingDiscount;
import com.jpmc.theater.service.pricing.SequenceDiscount;
import com.jpmc.theater.service.pricing.SpecialDiscount;
import com.jpmc.theater.service.pricing.TimeOfDayDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest implements IPricingTest {

    private PricingService pricingService;

    @BeforeEach
    public void setup() {
        List<IPricingDiscount> discountRuleList = new ArrayList<>();
        discountRuleList.add(new SpecialDiscount());
        discountRuleList.add(new SequenceDiscount());
        discountRuleList.add(new TimeOfDayDiscount());
        discountRuleList.add(new DateDiscount());
        pricingService = new PricingService(discountRuleList);
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
        assertEquals(TIME_DISCOUNT_PERCENT * TEST_TICKET_PRICE, pricingService.calculateDiscount(showing));
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
}
