package com.jpmc.theater.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * MovieSchedule - a bean container class for outputting the date and movie schedule in json
 */
@Data
@AllArgsConstructor
public class MovieSchedule {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate date;
    List<Showing> schedule;
}
