package org.littleus.sdk.config.dynamic.register;

import com.netflix.config.PolledConfigurationSource;
import org.littleus.sdk.config.dynamic.annotation.UserConfig;
import org.littleus.sdk.config.dynamic.annotation.UserConfigs;
import org.littleus.sdk.config.dynamic.util.BeanCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @author hyzhangj
 */
public class UserSourceRegister implements ImportBeanDefinitionRegistrar {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSourceRegister.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(UserConfig.class.getName());
        if (Objects.isNull(attributesMap)) {
            LOGGER.warn("user config annotation attributes is null or empty!");
            return;
        }

        AnnotationAttributes userConfig = AnnotationAttributes.fromMap(attributesMap);
        registerUserConfig(userConfig);
    }

    private static void registerUserConfig(AnnotationAttributes userConfig) {
        Class<? extends PolledConfigurationSource> userPooledSourceType = userConfig.getClass("value");
        PolledConfigurationSource source = BeanCreator.create(userPooledSourceType);
        int initialDelayMillis = userConfig.getNumber("initialDelayMillis");
        int delayMillis = userConfig.getNumber("delayMillis");

        ConfigRegisterHelper.register(source, initialDelayMillis, delayMillis);
    }

    public static class RepeatableRegister implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(UserConfigs.class.getName());
            if (Objects.isNull(attributesMap)) {
                LOGGER.warn("user configs annotation attributes is null or empty!");
                return;
            }

            AnnotationAttributes userConfigs = AnnotationAttributes.fromMap(attributesMap);
            AnnotationAttributes[] userConfigArr = userConfigs.getAnnotationArray("value");
            for (AnnotationAttributes userConfig : userConfigArr) {
                registerUserConfig(userConfig);
            }

        }
    }
}
