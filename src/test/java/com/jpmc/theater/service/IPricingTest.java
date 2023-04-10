package com.jpmc.theater.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * IPricingTest - interface class used to share constants used by the pricing service test class and the pricing discount
 * rule test classes.
 */
public interface IPricingTest {
    String TEST_MOVIE_TITLE = "Spider-Man: No Way Home";
    double TEST_TICKET_PRICE = 12.5;
    LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 6);
    LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);
    LocalDate DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 7);
    LocalTime DISCOUNT_TIME = LocalTime.of(12, 30);
    int NON_DISCOUNT_SEQUENCE = 5;
    int TIME_DISCOUNT_START_HOUR = 11; // 11 is 11:00am
    int TIME_DISCOUNT_END_HOUR = 16;   // 16 is 4:00pm
    int MOVIE_CODE_SPECIAL = 1;
    double TIME_DISCOUNT_PERCENT = 0.25; // 25% discount for movies starting between certain times

}
