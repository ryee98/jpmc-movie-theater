package com.jpmc.theater;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IPricingService;
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
        LOGGER.info("\nThe supported formats for the movie schedule are: " + scheduleService.getSupportedFormats());
        LOGGER.info("\nmovie schedule in text format\n" + scheduleService.generateSchedule(MediaType.TEXT_PLAIN_VALUE));
        LOGGER.info("\nmovie schedule in json format\n" + scheduleService.generateSchedule(MediaType.APPLICATION_JSON_VALUE));

        IPricingService pricingService = context.getBean(IPricingService.class);
        // Invoke the pricing service to demonstration the calculation of the discount
        Showing showing = scheduleService.getShowing(1);
        // discount should be 25% of ticket price which is the largest discount available
        double ticketPrice = pricingService.calculateTicketPrice(showing);
        LOGGER.info("\nThe discounted ticket price for the first showing is {}", ticketPrice);
        LOGGER.info("\nThe regular price for the movie {} is {}", showing.getMovie().getTitle(), showing.getMovie().getTicketPrice());
        LOGGER.info("\nThe discount is {}", pricingService.calculateDiscount(showing));
    }
}
