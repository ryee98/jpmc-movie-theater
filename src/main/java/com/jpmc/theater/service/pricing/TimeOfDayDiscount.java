package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;

public class TimeOfDayDiscount implements IPricingDiscount{

    public static final int TIME_DISCOUNT_START_HOUR = 11; // 11 is 11:00am
    public static final int TIME_DISCOUNT_END_HOUR = 16;   // 16 is 4:00pm
    public static final double TIME_DISCOUNT_PERCENT = 0.25; // 25% discount for movies starting between certain times

    /**
     * calculateDiscount - calculates the discount amount based on the time of the movie showing
     * @param showing - The movie showing
     * @return The discount amount or 0.0 if there is no discount
     */
    @Override
    public double calculateDiscount(Showing showing) {
        double discount = 0.0;
        if (showing.getStartTime().getHour() >= TIME_DISCOUNT_START_HOUR && showing.getStartTime().getHour() <= TIME_DISCOUNT_END_HOUR) {
            discount = showing.getMovie().getTicketPrice() * TIME_DISCOUNT_PERCENT;
        }
        return discount;
    }
}
