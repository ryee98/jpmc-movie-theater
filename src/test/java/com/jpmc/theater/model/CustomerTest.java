package com.jpmc.theater.model;

import com.jpmc.theater.util.AbstractPojoTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerTest extends AbstractPojoTester {
    public static final String TEST_NAME = "testName";
    public static final String TEST_ID = "testId";
    @Test
    public void testAllArgConstructor() {
        Customer customer = new Customer(null, null);
        assertNull(customer.getName());
        assertNull(customer.getId());

        customer = new Customer(TEST_NAME, TEST_ID);
        assertEquals(TEST_NAME, customer.getName());
        assertEquals(TEST_ID, customer.getId());
    }

    @Test
    public void testGettersAndSetters() {
        this.testGetterSetter(CustomerTest.class);
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(TEST_NAME, TEST_ID);
        assertEquals("name: " + TEST_NAME, customer.toString());
    }
}
