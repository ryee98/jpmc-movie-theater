package com.jpmc.theater.service;

import com.jpmc.theater.exception.ScheduleException;
import com.jpmc.theater.model.Showing;

import java.util.List;

/**
 * ISchedyleService - interface class that declares the functionality provided by the Schedule Service
 * Currently, the only functions defined are to retrieve the supported formats to generate a schedule in
 * a given format
 */
public interface IScheduleService {
    /**
     * getSupportedFormats - returns a list of the formats supported
     * @return A List of the formats that the schedule can be returned in.
     */
    List<String> getSupportedFormats();

    /**
     * generateSchedule - generate the schedule in the desired format.
     * @param format The format to return the schedule
     * @return The movie schedule in the format specified by the format parameter.
     * @throws - ScheduleException if an error occurred
     */
    String generateSchedule(String format) throws ScheduleException;

    /**
     * getShowing - retrieves a movie showing if the given sequence is valid.
     * @param sequence The 1-based sequence of the Showing that is requested.
     * @return The Showing that corresponds to the given sequence value
     * @throws ScheduleException if the given sequence value does not correspond
     * to a Showing in the schedule
     */
    Showing getShowing(int sequence);
}
