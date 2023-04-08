package com.jpmc.theater.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jpmc.theater.util.StringUtils;

import java.io.IOException;
import java.time.Duration;

/**
 * Created by ryee on 4/7/23
 */
public class DurationSerializer extends JsonSerializer<Duration> {

    @Override
    public void serialize(Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(StringUtils.formatDuration(duration));
    }
}
