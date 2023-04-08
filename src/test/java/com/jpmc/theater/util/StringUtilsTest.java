package com.jpmc.theater.util;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    public void testHandlePlurals() {
        // test with zero
        assertEquals("s", StringUtils.handlePlural(0));
        // test singular case
        assertEquals("", StringUtils.handlePlural(1));
        // test with 2
        assertEquals("s", StringUtils.handlePlural(2));
    }

    /**
     * testFormatDuration - tests the handling of different Durations including:
     * 0 minutes, 1 minute, 60 minutes, one hour and one minute, two hours and one minute
     */
    @Test
    public void testFormatDuration() {
        Duration duration = Duration.ofMinutes(0);
        String result = StringUtils.formatDuration(duration);
        assertEquals("0 minutes", result);

        duration = Duration.ofMinutes(1);
        result = StringUtils.formatDuration(duration);
        assertEquals("1 minute", result);

        duration = Duration.ofMinutes(60);
        result = StringUtils.formatDuration(duration);
        assertEquals("1 hour 0 minutes", result);

        duration = Duration.ofMinutes(61);
        result = StringUtils.formatDuration(duration);
        assertEquals("1 hour 1 minute", result);

        duration = Duration.ofMinutes(121);
        result = StringUtils.formatDuration(duration);
        assertEquals("2 hours 1 minute", result);
    }
}
