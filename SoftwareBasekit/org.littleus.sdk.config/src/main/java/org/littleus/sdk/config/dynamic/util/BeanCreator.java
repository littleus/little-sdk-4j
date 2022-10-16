package org.littleus.sdk.config.dynamic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author hyzhangj
 */
@Component
public class BeanCreator implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCreator.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanCreator.applicationContext = applicationContext;
    }

    public static <T> T create(Class<T> type) {
        if (applicationContext == null) {
            return newInstance(type);
        }

        try {
            return applicationContext.getBean(type);
        } catch (BeansException e) {
            LOGGER.error("create bean error", e);
            return newInstance(type);
        }
    }

    private static <T> T newInstance(Class<T> type) {
        Optional<Constructor<?>> noArgsConstructorOpt = Arrays.stream(type.getDeclaredConstructors()).filter(constructor -> constructor.getParameterCount() == 0).findFirst();
        Constructor<?> constructor = noArgsConstructorOpt.orElseThrow(() -> new IllegalArgumentException(""));
        try {
            return (T) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("instance bean error", e);
            throw new IllegalArgumentException(e);
        }
    }
}
