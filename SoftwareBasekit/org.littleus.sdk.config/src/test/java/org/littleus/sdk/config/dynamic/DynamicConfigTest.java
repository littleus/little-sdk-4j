package org.littleus.sdk.config.dynamic;

import com.netflix.config.DynamicProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Dynamic config test.
 *
 * @author hyzhangj
 * @since 2022 -09-25
 */
class DynamicConfigTest {

    /**
     * Gets boolean.
     */
    @Test
    void getBoolean() {
        Boolean booleanValue = DynamicConfig.getBoolean("boolean.value");
        assertTrue(booleanValue);
    }

    /**
     * Test get boolean.
     */
    @Test
    void testGetBoolean() {
        Boolean booleanValue = DynamicConfig.getBoolean("boolean.value", false);
        assertTrue(booleanValue);
    }

    /**
     * Gets integer.
     */
    @Test
    void getInteger() {
        Integer integerValue = DynamicConfig.getInteger("integer.value");
        assertEquals(10086, integerValue);
    }

    /**
     * Test get integer.
     */
    @Test
    void testGetInteger() {
        Integer integerValue = DynamicConfig.getInteger("integer.value", 1);
        assertEquals(10086, integerValue);
    }

    /**
     * Gets long.
     */
    @Test
    void getLong() {
        Long longValue = DynamicConfig.getLong("long.value");
        assertEquals(999999L, longValue);
    }

    /**
     * Test get long.
     */
    @Test
    void testGetLong() {
        Long longValue = DynamicConfig.getLong("long.value", 1L);
        assertEquals(999999L, longValue);
    }

    /**
     * Gets float.
     */
    @Test
    void getFloat() {
        Float floatValue = DynamicConfig.getFloat("float.value");
        assertEquals(9.9F, floatValue);
    }

    /**
     * Test get float.
     */
    @Test
    void testGetFloat() {
        Float floatValue = DynamicConfig.getFloat("float.value", 1F);
        assertEquals(9.9F, floatValue);
    }

    /**
     * Gets double.
     */
    @Test
    void getDouble() {
        Double doubleValue = DynamicConfig.getDouble("double.value");
        assertEquals(123.4D, doubleValue);
    }

    /**
     * Test get double.
     */
    @Test
    void testGetDouble() {
        Double doubleValue = DynamicConfig.getDouble("double.value", 1d);
        assertEquals(123.4D, doubleValue);
    }

    /**
     * Gets string.
     */
    @Test
    void getString() {
        String stringValue = DynamicConfig.getString("string.value");
        assertEquals("Hello World", stringValue);
    }

    /**
     * Test get string.
     */
    @Test
    void testGetString() {
        String stringValue = DynamicConfig.getString("string.value", "xxx");
        assertEquals("Hello World", stringValue);
    }

    /**
     * Add call back.
     */
    @Test
    void addCallBack() {
        Runnable stringCallBack = () -> System.out.println("=======string value changed=======");
        DynamicConfig.addCallBack("string.value", stringCallBack);
        boolean removeCallback = DynamicProperty.getInstance("string.value").removeCallback(stringCallBack);
        assertTrue(removeCallback);
    }
}