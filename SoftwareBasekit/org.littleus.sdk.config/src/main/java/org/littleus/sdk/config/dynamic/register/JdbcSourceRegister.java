package org.littleus.sdk.config.dynamic.register;

import com.netflix.config.*;
import org.littleus.sdk.config.dynamic.annotation.JdbcConfig;
import org.littleus.sdk.config.dynamic.annotation.JdbcConfigs;
import org.littleus.sdk.config.dynamic.source.jdbc.LazyJdbcSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author hyzhangj
 */
public class JdbcSourceRegister implements ImportBeanDefinitionRegistrar {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSourceRegister.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(JdbcConfig.class.getName());
        if (Objects.isNull(attributesMap)) {
            LOGGER.warn("jdbc config annotation attributes is null or empty!");
            return;
        }

        AnnotationAttributes jdbcConfig = AnnotationAttributes.fromMap(attributesMap);
        registerJdbcConfig(jdbcConfig);
    }

    private static void registerJdbcConfig(AnnotationAttributes jdbcConfig) {
        Class<? extends Supplier<DataSource>> dataSource = jdbcConfig.getClass("dataSource");
        String querySql = jdbcConfig.getString("querySql");
        String keyColName = jdbcConfig.getString("keyColName");
        String valueColName = jdbcConfig.getString("valueColName");
        int initialDelayMillis = jdbcConfig.getNumber("initialDelayMillis");
        int delayMillis = jdbcConfig.getNumber("delayMillis");

        PolledConfigurationSource source = new LazyJdbcSource(dataSource, querySql, keyColName, valueColName);
        ConfigRegisterHelper.register(source, initialDelayMillis, delayMillis);
    }

    public static class RepeatableRegister implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(JdbcConfigs.class.getName());
            if (Objects.isNull(attributesMap)) {
                LOGGER.warn("jdbc configs annotation attributes is null or empty!");
                return;
            }

            AnnotationAttributes jdbcConfigs = AnnotationAttributes.fromMap(attributesMap);
            AnnotationAttributes[] jdbcConfigArr = jdbcConfigs.getAnnotationArray("value");
            for (AnnotationAttributes jdbcConfig : jdbcConfigArr) {
                registerJdbcConfig(jdbcConfig);
            }
        }
    }
}
