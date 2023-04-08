package com.jpmc.theater.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.exception.ScheduleException;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleServiceTest {

    private static final String UNSUPPORTED_SCHEDULE_FORMAT = "application/xml";
    private static final String ORIGINAL_TEXT_OUTPUT = "2023-04-07\n" +
            "===================================================" +
            "1: 2023-04-07T09:00 Turning Red (1 hour 25 minutes) $11.0" +
            "2: 2023-04-07T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
            "3: 2023-04-07T12:50 The Batman (1 hour 35 minutes) $9.0\n" +
            "4: 2023-04-07T14:30 Turning Red (1 hour 25 minutes) $11.0\n" +
            "5: 2023-04-07T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
            "6: 2023-04-07T17:50 The Batman (1 hour 35 minutes) $9.0\n" +
            "7: 2023-04-07T19:30 Turning Red (1 hour 25 minutes) $11.0\n" +
            "8: 2023-04-07T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
            "9: 2023-04-07T23:00 The Batman (1 hour 35 minutes) $9.0\n" +
            "===================================================\n";

    private static final String TEST_JSON_SHOWING = "{\n" +
            "  \"movie\" : {\n" +
            "    \"title\" : \"The Batman\",\n" +
            "    \"runningTime\" : \"1 hour 35 minutes\",\n" +
            "    \"ticketPrice\" : 9.0,\n" +
            "    \"specialCode\" : 0\n" +
            "  },\n" +
            "  \"sequenceOfTheDay\" : 3,\n" +
            "  \"movieFee\" : 9.0,\n" +
            "  \"startTime\" : \"04-07-2023 12:50PM\"\n" +
            "}";
    private static final String TEST_TEXT_SHOWING = "2: 11:00AM Spider-Man: No Way Home (1 hour 30 minutes) $12.50";
    private ScheduleService scheduleService;

    @BeforeEach
    public void setup() {
        List<Showing> schedule = createScheduleData();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        LocalDateProvider localDateProvider = new LocalDateProvider();
        scheduleService = new ScheduleService(schedule, objectMapper, localDateProvider);
    }

    @Test
    public void testGenerateScheduleFormat() {
        String scheduleString = scheduleService.generateSchedule(ScheduleService.TEXT_FORMAT);
        assertTrue(scheduleString.contains(TEST_TEXT_SHOWING));

        scheduleString = scheduleService.generateSchedule(ScheduleService.JSON_FORMAT);
        System.out.println(TEST_JSON_SHOWING);
        System.out.println(scheduleString);
        assertTrue(scheduleString.contains(TEST_JSON_SHOWING));
    }

    @Test()
    public void testGenerateScheduleFormatWithUnsupportedFormat() {
        Exception exception = assertThrows(ScheduleException.class, () -> {
            scheduleService.generateSchedule(UNSUPPORTED_SCHEDULE_FORMAT);
        });
        assertEquals("unsupported schedule format: " + UNSUPPORTED_SCHEDULE_FORMAT, exception.getMessage());
    }

    @Test
    public void testGenerateTextSchedule() {
        String textSchedule = scheduleService.generateTextSchedule();
        assertTrue(textSchedule.contains(TEST_TEXT_SHOWING));
        System.out.println(textSchedule);
        assertTrue(textSchedule.contains("10: 11:30PM Upcoming Previews (12 minutes) $0.00"));
    }

    @Test
    public void testGenerateJsonSchedule() throws JsonProcessingException {
        String jsonSchedule = scheduleService.generateJsonSchedule();
        System.out.println(jsonSchedule);
    }

    /**
     * test valid Showings from the schedule
     */
    @Test
    public void testGetShowingWithValidSequence() {
        // valid sequences are 1-10
        for (int i=1; i <=10; i++) {
            Showing showing = scheduleService.getShowing(1);
            assertNotNull(showing);
        }
    }

    @Test
    public void testGetShowingForInvalidSequenceValue() {
        Exception exception = assertThrows(ScheduleException.class, () -> {
            scheduleService.getShowing(0);
        });
        assertEquals("invalid sequence value: 0", exception.getMessage());

        exception = assertThrows(ScheduleException.class, () -> {
            scheduleService.getShowing(11);
        });
        assertEquals("invalid sequence value: 11", exception.getMessage());
    }

    private List<Showing> createScheduleData() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Movie movieShort = new Movie("Upcoming Previews", Duration.ofMinutes(12), 0, 0);
        List<Showing> schedule;
        LocalDate today = LocalDate.now();
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(today, LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(today, LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(today, LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(today, LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(today, LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(today, LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(today, LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(today, LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(today, LocalTime.of(23, 0))),
                new Showing(movieShort, 10, LocalDateTime.of(today, LocalTime.of(23, 30)))
        );
        return schedule;
    }
}
