package com.jpmc.theater.model;

import com.jpmc.theater.util.AbstractPojoTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest extends AbstractPojoTester {

    @Test
    public void testGettersAndSetters() {
        this.testGetterSetter(MovieTest.class);
    }
}
