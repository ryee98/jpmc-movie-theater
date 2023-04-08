package com.jpmc.theater.exception;

/**
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
