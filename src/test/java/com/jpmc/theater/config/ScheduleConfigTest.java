package com.jpmc.theater.config;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.LocalDateProvider;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleConfigTest {

    /**
     * Test movie showtime schedule is configured
     * Verify that 10 show times exist in the schedule and that
     */
    @Test
    public void testGetSchedule() {
        ScheduleConfig scheduleConfig = new ScheduleConfig(new LocalDateProvider());
        List<Showing> schedule = scheduleConfig.getSchedule();
        assertEquals(10, schedule.size());
        LocalDate today = LocalDate.now();
        Movie movieShort = new Movie("Upcoming Previews", Duration.ofMinutes(12), 0, 0);
        Showing showing = new Showing(movieShort, 10, LocalDateTime.of(today, LocalTime.of(23, 30)));
        assertTrue(schedule.contains(showing));
    }
}
