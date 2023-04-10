package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SequenceDiscount implements IPricingDiscount {
    @Value("${first.show.discount:3.00}")
    private double firstShowDiscount = 3.00;
    @Value("${second.show.discount:2.00}")
    public static final double secondShowDiscount = 2.00;
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
            discount = firstShowDiscount;
        } else if (showSequence == 2) {
            discount = secondShowDiscount;
        }
        return discount;
    }
}
