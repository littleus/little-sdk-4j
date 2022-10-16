package org.littleus.sdk.config.dynamic.annotation;

import com.netflix.config.PolledConfigurationSource;
import org.littleus.sdk.config.dynamic.register.JdbcSourceRegister;
import org.littleus.sdk.config.dynamic.register.UserSourceRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author hyzhangj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(UserConfigs.class)
@Import(UserSourceRegister.class)
public @interface UserConfig {
    Class<? extends PolledConfigurationSource> value();

    int initialDelayMillis() default 10000;

    int delayMillis() default 300000;
}
