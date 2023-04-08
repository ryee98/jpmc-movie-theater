package com.jpmc.theater.service;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + new LocalDateProvider().currentDate());
    }
}
