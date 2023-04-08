package com.jpmc.theater.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jpmc.theater.util.json.DurationSerializer;
import lombok.Data;

import java.time.Duration;

@Data
public class Movie {

    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonSerialize(using = DurationSerializer.class)
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

}
