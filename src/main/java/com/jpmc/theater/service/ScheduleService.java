package com.jpmc.theater.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.exception.ScheduleException;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ScheduleService - service class that is responsible for providing schedule services.
 * Currently, it generates the moving showing schedule in text and json formats.
 *
 * Created by ryee on 4/7/23
 */
@Service
public class ScheduleService {

    public static final int DEFAULT_SIZE = 1024;
    public static final String NEWLINE = "\n";
    public static final String JSON_FORMAT = MediaType.APPLICATION_JSON_VALUE;
    public static final String TEXT_FORMAT = MediaType.TEXT_PLAIN_VALUE;
    public static final Set<String> SUPPORTED_SCHEDULE_FORMATS = new HashSet<>();

    static {
        Collections.addAll(SUPPORTED_SCHEDULE_FORMATS, JSON_FORMAT, TEXT_FORMAT);
    }

    private List<Showing> schedule;
    private ObjectMapper objectMapper;

    private LocalDateProvider localDateProvider;

    @Autowired
    public ScheduleService(List<Showing> schedule, ObjectMapper objectMapper, LocalDateProvider localDateProvider) {
        this.schedule = schedule;
        this.objectMapper = objectMapper;
        this.localDateProvider = localDateProvider;
    }

    /**
     * getShowing - retrieves a movie showing if the given sequence is valid.
     * @param sequence The 1-based sequence of the Showing that is requested.
     * @return The Showing that corresponds to the given sequence value
     * @throws ScheduleException if the given sequence value does not correspond
     * to a Showing in the schedule
     */
    public Showing getShowing(int sequence) throws ScheduleException {
        // 1 is subtracted from the sequence value b/c the Showings are stored in a List whose entries are zero-based
        if (sequence < 1 || sequence > schedule.size()-1) {
            throw new ScheduleException("invalid sequence value: " + sequence);
        }
        return (schedule.get(sequence-1));
    }

    /**
     * generateSchedule - generates the movie schedule using the supplied format
     *
     * @param format - the format of the schedule to be generated. Currently, "text/plain" and "application/json" are supported.
     *
     * @return A String representing the schedule in the format provided
     * @throws ScheduleException - if the schedule could not be generated for the given format.
     */
    public String generateSchedule(String format) throws ScheduleException {
        String response;
        switch(format) {
            case MediaType.TEXT_PLAIN_VALUE:
                response = generateTextSchedule();
                break;
            case MediaType.APPLICATION_JSON_VALUE:
                try {
                    response = generateJsonSchedule();
                } catch(JsonProcessingException jpe) {
                    throw new ScheduleException(jpe);
                }
                break;
            default:
                throw new ScheduleException(String.format("unsupported schedule format: " + format));
        }
        return response;
    }

    protected String generateTextSchedule() {
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_SIZE);
        stringBuilder.append(localDateProvider.currentDate());
        stringBuilder.append(NEWLINE);
        stringBuilder.append("===================================================\n");
        schedule.forEach(s ->
                        stringBuilder.append(String.format("%1$d: %2$s %3$s (%4$s) $%5$4.2f\n",s.getSequenceOfTheDay(), StringUtils.formatDateTime(s.getStartTime()),
                                s.getMovie().getTitle(), StringUtils.formatDuration(s.getMovie().getRunningTime()),s.getMovieFee())));
        stringBuilder.append("===================================================\n");
        return stringBuilder.toString();
    }

    protected String generateJsonSchedule() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(schedule);
        return json;
    }

}
