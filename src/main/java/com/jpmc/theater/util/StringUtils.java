package com.jpmc.theater.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * StringUtils - string utility class
 *
 * Created by ryee on 4/6/23
 */
public class StringUtils {
    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mma");
    /**
     * private no-arg constructor to prevent instantiation
     */
    private StringUtils() {

    }

    /**
     *  handlePlural - utility function used for postfixing (s) to handle plural correctly
     * @param value
     * @return
     */
    public static String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }

    public static String formatDuration(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        if (hour == 0) {
            return String.format("%s minute%s", remainingMin, StringUtils.handlePlural(remainingMin));
        } else {
            return String.format("%s hour%s %s minute%s", hour, StringUtils.handlePlural(hour), remainingMin, StringUtils.handlePlural(remainingMin));
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
