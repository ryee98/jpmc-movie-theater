package com.jpmc.theater.service.pricing;

import com.jpmc.theater.model.Showing;

public interface IPricingDiscount {
    double calculateDiscount(Showing showing);
}
