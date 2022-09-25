package org.littleus.sdk.config.dynamic;

import com.netflix.config.DynamicProperty;

/**
 * The type Dynamic config.
 *
 * @author hyzhangj
 * @since 2022 -09-25
 */
public class DynamicConfig {
    /**
     * Gets boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public static Boolean getBoolean(String key) {
        return DynamicProperty.getInstance(key).getBoolean();
    }

    /**
     * Gets boolean.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean
     */
    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return DynamicProperty.getInstance(key).getBoolean(defaultValue);
    }

    /**
     * Gets integer.
     *
     * @param key the key
     * @return the integer
     */
    public static Integer getInteger(String key) {
        return DynamicProperty.getInstance(key).getInteger();
    }

    /**
     * Gets integer.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the integer
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        return DynamicProperty.getInstance(key).getInteger(defaultValue);
    }

    /**
     * Gets long.
     *
     * @param key the key
     * @return the long
     */
    public static Long getLong(String key) {
        return DynamicProperty.getInstance(key).getLong();
    }

    /**
     * Gets long.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the long
     */
    public static Long getLong(String key, Long defaultValue) {
        return DynamicProperty.getInstance(key).getLong(defaultValue);
    }

    /**
     * Gets float.
     *
     * @param key the key
     * @return the float
     */
    public static Float getFloat(String key) {
        return DynamicProperty.getInstance(key).getFloat();
    }

    /**
     * Gets float.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the float
     */
    public static Float getFloat(String key, Float defaultValue) {
        return DynamicProperty.getInstance(key).getFloat(defaultValue);
    }

    /**
     * Gets double.
     *
     * @param key the key
     * @return the double
     */
    public static Double getDouble(String key) {
        return DynamicProperty.getInstance(key).getDouble();
    }

    /**
     * Gets double.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the double
     */
    public static Double getDouble(String key, Double defaultValue) {
        return DynamicProperty.getInstance(key).getDouble(defaultValue);
    }

    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     */
    public static String getString(String key) {
        return DynamicProperty.getInstance(key).getString();
    }

    /**
     * Gets string.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String getString(String key, String defaultValue) {
        return DynamicProperty.getInstance(key).getString(defaultValue);
    }

    /**
     * Add call back.
     *
     * @param key      the key
     * @param callBack the call back
     */
    public static void addCallBack(String key, Runnable callBack) {
        DynamicProperty.getInstance(key).addCallback(callBack);
    }
}