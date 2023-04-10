package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;
import org.springframework.stereotype.Component;

@Component
public class SequenceDiscount implements IPricingDiscount {
    public static final double FIRST_SHOW_DISCOUNT = 3.00;
    public static final double SECOND_SHOW_DISCOUNT = 2.00;
    /**
     * calculateDiscount - calculates the amount of discount based on the showing sequence;
     * @param showing - The movie showing
     * @return The discount amount or 0.0 if there is no discount
     */
    @Override
    public double calculateDiscount(Showing showing) {
        double discount = 0.0;
        int showSequence = showing.getSequenceOfTheDay();

        if (showing.getSequenceOfTheDay() == 1) {
            discount = FIRST_SHOW_DISCOUNT;
        } else if (showSequence == 2) {
            discount = SECOND_SHOW_DISCOUNT;
        }
        return discount;
    }
}
