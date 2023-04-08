package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

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
