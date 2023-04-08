package com.jpmc.theater.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class ScheduleExceptionTest {

    @Test
    public void testStringConstructor() {
        ScheduleException se = new ScheduleException("test message");
        assertEquals("test message", se.getMessage());
        se = new ScheduleException((String) null);
        assertNull(se.getMessage());
    }

    @Test
    public void testThrowableConstructor() {
        JsonProcessingException mockJsonProcessingException = mock(JsonProcessingException.class);
        ScheduleException se = new ScheduleException(mockJsonProcessingException);
        assertEquals(mockJsonProcessingException, se.getCause());
        se = new ScheduleException((String) null);
        assertNull(se.getMessage());
    }
}
