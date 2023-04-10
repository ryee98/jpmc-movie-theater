package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;
import org.springframework.stereotype.Component;

@Component
public class DateDiscount implements IPricingDiscount {
    public static final int DATE_DISCOUNT_DAY = 7; // 7th of the month is discount day
    public static final double DATE_DISCOUNT_AMOUNT = 1.00;

    /**
     * calculateDiscount - calculates the discount amount (if any) based on the date of the showing
     * @param showing - The showing information
     * @return The discount amount or 0.0 if there is no discount
     */
    @Override
    public double calculateDiscount(Showing showing) {
        double discount = 0.0;
        if (showing.getStartTime().getDayOfMonth() == DATE_DISCOUNT_DAY) {
            discount = DATE_DISCOUNT_AMOUNT;
        }
        return discount;
    }
}
