package org.littleus.sdk.config.dynamic.annotation;

import org.littleus.sdk.config.dynamic.register.JdbcSourceRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hyzhangj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(JdbcSourceRegister.RepeatableRegister.class)
public @interface JdbcConfigs {
    JdbcConfig[] value();
}
