package com.jpmc.theater.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * LocalDateProvider - Spring component that provides a method for returning the current date.
 * This class was changed to be a Spring component bean which is a singleton by default. This class helps with
 * unit testing since it provides method that can be mocked to return different dates when testing.
 */
@Component
public class LocalDateProvider {

    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
