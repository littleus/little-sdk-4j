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
@Import(UserSourceRegister.RepeatableRegister.class)
public @interface UserConfigs {
    UserConfig[] value();
}
