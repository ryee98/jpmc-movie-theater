package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateDiscount implements IPricingDiscount {

    // obtain values from properties file
    @Value("${discount.date.day:7}")
    public int discountDateDay = 7; // 7th of the month is discount day
    @Value("${discount.date.amount: 1.00}")
    public double dateDiscountAmount = 1.00;

    /**
     * calculateDiscount - calculates the discount amount (if any) based on the date of the showing
     * @param showing - The showing information
     * @return The discount amount or 0.0 if there is no discount
     */
    @Override
    public double calculateDiscount(Showing showing) {
        double discount = 0.0;
        if (showing.getStartTime().getDayOfMonth() == discountDateDay) {
            discount = dateDiscountAmount;
        }
        return discount;
    }
}
