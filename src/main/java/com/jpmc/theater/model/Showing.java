package com.jpmc.theater.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;

    private double movieFee;
    @JsonFormat(pattern="MM-dd-yyyy h:mma") // formats the LocalDateTime as date/time pattern instead of the default [ YYYY, M, d, H, m ] format
    private LocalDateTime startTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime startTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.startTime = startTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

 //   private double calculateFee(int audienceCount) {
 //       return movie.calculateTicketPrice(this) * audienceCount;
 //   }
}
