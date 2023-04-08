package com.jpmc.theater.config;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.LocalDateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by ryee on 4/7/23
 */
@Configuration
public class ScheduleConfig {

    LocalDateProvider localDateProvider;

    @Autowired
    public ScheduleConfig(LocalDateProvider localDateProvider) {
        this.localDateProvider = localDateProvider;
    }
    @Bean
    public List<Showing> getSchedule() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Movie movieShort = new Movie("Upcoming Previews", Duration.ofMinutes(12), 0, 0);
        List<Showing> schedule;
        LocalDate today = localDateProvider.currentDate();
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
                new Showing(movieShort, 0, LocalDateTime.of(today, LocalTime.of(8, 45)))
        );
        return schedule;
    }
}
