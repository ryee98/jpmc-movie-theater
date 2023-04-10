package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.pricing.IPricingDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PricingService - service class that implements pricing rules and provides methods for
 * calculating discounts and ticket prices.
 *
 * Created by ryee on 4/6/23
 */
@Service
public class PricingService implements IPricingService {

    private List<IPricingDiscount> discountRules;
    @Autowired
    public PricingService(List<IPricingDiscount> discountRules) {
        this.discountRules = discountRules;
    }

    public double calculateTicketPrice(Showing showing) {
        return Math.max(showing.getMovie().getTicketPrice() - calculateDiscount(showing), 0.0); // prevent negative ticket pricing
    }

    /**
     * calculateDiscount - calculates the discount off of the ticket price based on movie sequence or special status of movie.
     * Value can be 0.0 if no discount rules apply.
     *
     * @param showing - the movie showing
     * @return 0.0 if no discounts apply, otherwise greater of the special discount or the sequence discount
     */
    public double calculateDiscount(Showing showing) {

        List<Double> discountList = new ArrayList<>();
        for (IPricingDiscount discountRule : discountRules) {
            double discount = discountRule.calculateDiscount(showing);
            discountList.add(discount);
        }

        return Collections.max(discountList);
    }
}
