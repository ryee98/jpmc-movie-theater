package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;

/**
 * IPricingDiscount - interface class that is implemented by discount classes to allow for new discount
 * rules to be added more easily. To add a new discount rule, a class just needs to implement this interface and
 * be made a SPpring component
 */
public interface IPricingDiscount {
    /**
     * calculateDiscount - the method that will be called on the discount rule class which will return the discount
     * amount.
     * @param showing - The movie showing to calculate the discount on.
     * @return The discount amount or 0.0 if no discount is available for the given rule class.
     */
    double calculateDiscount(Showing showing);
}
