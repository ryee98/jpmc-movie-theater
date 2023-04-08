package com.jpmc.theater;

import com.jpmc.theater.service.LocalDateProvider;
import com.jpmc.theater.service.PricingService;
import com.jpmc.theater.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by ryee on 4/7/23
 */
@SpringBootTest
public class MovieTheaterAppTest {

    @Autowired
    LocalDateProvider localDateProvider;
    @Autowired
    PricingService pricingService;

    @Autowired
    ScheduleService scheduleService;
    @Test
    void contextLoads() {
        assertNotNull(localDateProvider);
        assertNotNull(pricingService);
        assertNotNull(scheduleService);
    }

    @Test
    public void testScheduleServiceProducesTextSchedule() {
        scheduleService.generateSchedule(ScheduleService.TEXT_FORMAT);
    }

    @Test
    public void testScheduleServiceProducesJsonSchedule() {
        scheduleService.generateSchedule(ScheduleService.JSON_FORMAT);
    }

}
