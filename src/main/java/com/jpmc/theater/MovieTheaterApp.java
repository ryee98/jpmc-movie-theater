package com.jpmc.theater;

import com.jpmc.theater.service.IScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;

/**
 * Created by ryee on 4/7/23
 */
@SpringBootApplication
public class MovieTheaterApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterApp.class);
    private static ApplicationContext context;
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MovieTheaterApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        context = application.run(args);
        // Invoke the services to demonstrate the functionality
        IScheduleService scheduleService = context.getBean(IScheduleService.class);
        LOGGER.info("movie schedule in text format\n" + scheduleService.generateSchedule(MediaType.TEXT_PLAIN_VALUE));
        LOGGER.info("movie schedule in json format\n" + scheduleService.generateSchedule(MediaType.APPLICATION_JSON_VALUE));
    }
}
