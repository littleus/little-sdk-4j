package org.littleus.sdk.config.dynamic.register;

import com.netflix.config.*;
import org.apache.commons.configuration.AbstractConfiguration;
import org.littleus.sdk.config.dynamic.schedule.ScheduleFactory;

/**
 * @author hyzhangj
 */
public class ConfigRegisterHelper {
    public static void register(PolledConfigurationSource source, int initialDelayMillis, int delayMillis) {
        AbstractConfiguration configInstance = ConfigurationManager.getConfigInstance();
        if (configInstance instanceof AggregatedConfiguration) {
            AbstractPollingScheduler scheduler = ScheduleFactory.INSTANCE.getScheduler(initialDelayMillis, delayMillis);
            AbstractConfiguration dynamicConfiguration = new DynamicConfiguration(source, scheduler);

            AggregatedConfiguration aggregatedConfiguration = AggregatedConfiguration.class.cast(configInstance);
            aggregatedConfiguration.addConfiguration(dynamicConfiguration);
        }
    }
}
