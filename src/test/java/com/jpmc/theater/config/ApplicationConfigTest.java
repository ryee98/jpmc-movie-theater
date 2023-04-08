package com.jpmc.theater.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationConfigTest {

    @Test
    public void testObjectMapper()  {
        ApplicationConfig applicationConfig = new ApplicationConfig();

        assertNotNull(applicationConfig.objectMapper());
    }
}
