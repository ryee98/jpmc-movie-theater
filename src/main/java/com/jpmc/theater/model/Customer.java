package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Customer - a model class that represents a customer
 */
@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private String id;

    @Override
    public String toString() {
        return "name: " + name;
    }
}
