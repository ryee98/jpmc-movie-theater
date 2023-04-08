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

    @Test
    public void testGetSchedule() {
        ScheduleConfig scheduleConfig = new ScheduleConfig(new LocalDateProvider());
        List<Showing> schedule = scheduleConfig.getSchedule();
        assertEquals(10, schedule.size());
        LocalDate today = LocalDate.now();
        Movie movieShort = new Movie("Upcoming Previews", Duration.ofMinutes(12), 0, 0);
        Showing showing = new Showing(movieShort, 0, LocalDateTime.of(today, LocalTime.of(8, 45)));
        assertTrue(schedule.contains(showing));
    }
}
