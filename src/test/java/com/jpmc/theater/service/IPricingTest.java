package com.jpmc.theater.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * IPricingTest - interface class used to share constants used by the pricing service test class and the pricing discount
 * rule test classes.
 */
public interface IPricingTest {
    static final String TEST_MOVIE_TITLE = "Spider-Man: No Way Home";
    static final double TEST_TICKET_PRICE = 12.5;
    static final LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 6);
    static final LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);
    static final LocalDate DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 7);
    static final LocalTime DISCOUNT_TIME = LocalTime.of(12, 30);
    static final int NON_DISCOUNT_SEQUENCE = 5;
}
