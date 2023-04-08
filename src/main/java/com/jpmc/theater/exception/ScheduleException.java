package com.jpmc.theater.exception;

/**
 * ScheduleException - a checked exception that is thrown by some of the ScheduleService methods.
 *
 * Created by ryee on 4/7/23
 */
public class ScheduleException extends RuntimeException {

    public ScheduleException(String message) {
        super(message);
    }

    public ScheduleException(Throwable t) {
        super(t);
    }
}
