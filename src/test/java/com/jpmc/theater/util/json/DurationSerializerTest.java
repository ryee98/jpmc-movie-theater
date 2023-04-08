package com.jpmc.theater.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * DurationSerializerTest - Unit test class for DurationSerializer
 */
class DurationSerializerTest {

    private DurationSerializer durationSerializer;
    JsonGenerator mockJsonGenerator;
    private String param;
    @BeforeEach
    public void setup() throws IOException {
        durationSerializer = new DurationSerializer();
        mockJsonGenerator = mock(JsonGenerator.class);
        doAnswer((InvocationOnMock invocation) -> {
            Object arg0 = invocation.getArgument(0);

            param= (String) arg0;
            return null;
        }).when(mockJsonGenerator).writeString(anyString());

        param = null;
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testDurationSerializerSerializeHourAndMinutes() throws IOException {
        SerializerProvider mockSerializerProvider = mock(SerializerProvider.class);
        Duration duration = Duration.ofMinutes(90);
        durationSerializer.serialize(duration, mockJsonGenerator, mockSerializerProvider);
        assertEquals("1 hour 30 minutes", param);
    }

    @Test
    public void testDurationSerializerSerializeOneMinute() throws IOException {
        SerializerProvider mockSerializerProvider = mock(SerializerProvider.class);
        Duration duration = Duration.ofMinutes(1);
        durationSerializer.serialize(duration, mockJsonGenerator, mockSerializerProvider);
        assertEquals("1 minute", param);
    }

    @Test
    public void testDurationSerializerSerializeMultipleHours() throws IOException {
        SerializerProvider mockSerializerProvider = mock(SerializerProvider.class);
        Duration duration = Duration.ofMinutes(121);
        durationSerializer.serialize(duration, mockJsonGenerator, mockSerializerProvider);
        assertEquals("2 hours 1 minute", param);
    }
}
