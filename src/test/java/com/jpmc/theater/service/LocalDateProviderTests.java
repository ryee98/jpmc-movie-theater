package com.jpmc.theater.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    /**
     * testCurrentTime - tests the currentTime() method of LocalDateProvider
     *
     */
    @Test
    void testCurrentTime() {
        LocalDateProvider localDateProvider = new LocalDateProvider();
        LocalDate now = LocalDate.now();
        assertEquals(now, localDateProvider.currentDate());

    }
}
