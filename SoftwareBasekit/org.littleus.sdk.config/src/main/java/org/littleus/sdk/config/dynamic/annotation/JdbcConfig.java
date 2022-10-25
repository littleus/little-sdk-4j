package org.littleus.sdk.config.dynamic.annotation;

import org.littleus.sdk.config.dynamic.dependency.ISourceDependency;
import org.littleus.sdk.config.dynamic.dependency.NonDenpendency;
import org.littleus.sdk.config.dynamic.register.JdbcSourceRegister;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.lang.annotation.*;
import java.util.function.Supplier;

/**
 * @author hyzhangj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(JdbcConfigs.class)
@Import(JdbcSourceRegister.class)
public @interface JdbcConfig {
    Class<? extends Supplier<DataSource>> dataSource();

    String querySql();

    String keyColName();

    String valueColName();

    int initialDelayMillis() default 10000;

    int delayMillis() default 30000;

    Class<? extends ISourceDependency> dependsOn() default NonDenpendency.class;
}
