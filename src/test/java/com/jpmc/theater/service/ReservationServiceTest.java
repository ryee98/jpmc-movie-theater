package com.jpmc.theater.service;

import com.jpmc.theater.config.ApplicationConfig;
import com.jpmc.theater.exception.ReservationException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.pricing.DateDiscount;
import com.jpmc.theater.service.pricing.IPricingDiscount;
import com.jpmc.theater.service.pricing.SequenceDiscount;
import com.jpmc.theater.service.pricing.SpecialDiscount;
import com.jpmc.theater.service.pricing.TimeOfDayDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationServiceTest {

    private ReservationService reservationService;

    @BeforeEach
    public void setup() {
        LocalDateProvider localDateProvider = new LocalDateProvider();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        ScheduleService scheduleService = new ScheduleService(createScheduleData(), applicationConfig.objectMapper(),
                localDateProvider);
        PricingService pricingService = constructPricingService();
        reservationService = new ReservationService(scheduleService, pricingService);
    }

    /**
     * testMakeReservation
     */
    @Test
    public void testMakeReservation() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing( new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5,
                1), 2, LocalDateTime.of(2023, Month.APRIL,6,11,0));
        Reservation reservation = reservationService.makeReservation(customer, showing, 3);
        assertEquals(customer, reservation.getCustomer());
        assertEquals(showing, reservation.getShowing());
        assertEquals(3, reservation.getPartyCount());
        assertEquals(28.125, reservationService.calculateFee(reservation));
    }

    @Test
    public void testMakeReservationWithNullCustomer() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing( new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5,
                1), 2, LocalDateTime.of(2023, Month.APRIL,6,11,0));
        Exception exception = assertThrows(ReservationException.class, () -> reservationService.makeReservation(null,
                showing, 2 ));
        assertEquals("customer is required", exception.getMessage());
    }

    @Test
    public void testMakeReservationWithNullCustomerName() {
        Customer customer = new Customer(null, "unused-id");
        Showing showing = new Showing( new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5,
                1), 2, LocalDateTime.of(2023, Month.APRIL,6,11,0));

        Exception exception = assertThrows(ReservationException.class, () -> {
            reservationService.makeReservation(null, showing, 2 );
        });
        assertEquals("customer is required", exception.getMessage());
    }

    @Test
    public void testMakeReservationWithBlankCustomerName() {
        Customer customer = new Customer("", "unused-id");
        Showing showing = new Showing( new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5,
                1), 2, LocalDateTime.of(2023, Month.APRIL,6,11,0));
        Exception exception = assertThrows(ReservationException.class, () -> {
            reservationService.makeReservation(customer, showing, 2 );
        });
        assertEquals("customer name is required", exception.getMessage());
    }

    @Test
    public void testCalculateFee() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing( new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0,
                0), 1, LocalDateTime.of(2023, Month.APRIL,7,12,0));
        Reservation reservation = reservationService.makeReservation(customer, showing, 3);
        assertEquals(27.0, reservationService.calculateFee(reservation));
    }

    private List<Showing> createScheduleData() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Movie movieShort = new Movie("Upcoming Previews", Duration.ofMinutes(12), 0, 0);
        List<Showing> schedule;
        LocalDate today = LocalDate.of(2023, Month.APRIL, 6);
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

    public PricingService constructPricingService() {
        PricingService pricingService;
        List<IPricingDiscount> discountRuleList = new ArrayList<>();
        IPricingDiscount specialDiscount = new SpecialDiscount();
        discountRuleList.add(specialDiscount);
        IPricingDiscount sequenceDiscount = new SequenceDiscount();
        discountRuleList.add(sequenceDiscount);
        IPricingDiscount timeOfDayDiscount = new TimeOfDayDiscount();
        discountRuleList.add(timeOfDayDiscount);
        IPricingDiscount dateDiscount = new DateDiscount();
        discountRuleList.add(dateDiscount);
        pricingService = new PricingService(discountRuleList);
        return pricingService;
    }
}