package org.littleus.sdk.config.dynamic.register;

import com.netflix.config.*;
import org.apache.commons.configuration.AbstractConfiguration;
import org.littleus.sdk.config.dynamic.schedule.SchedulerFactory;

/**
 * @author hyzhangj
 */
public class SourceRegisterHelper {
    public static void register(PolledConfigurationSource source, int initialDelayMillis, int delayMillis) {
        AbstractConfiguration configInstance = ConfigurationManager.getConfigInstance();
        if (configInstance instanceof AggregatedConfiguration) {
            AbstractPollingScheduler scheduler = SchedulerFactory.INSTANCE.getScheduler(initialDelayMillis, delayMillis);
            AbstractConfiguration dynamicConfiguration = new DynamicConfiguration(source, scheduler);

            AggregatedConfiguration aggregatedConfiguration = AggregatedConfiguration.class.cast(configInstance);
            aggregatedConfiguration.addConfiguration(dynamicConfiguration);
        }
    }
}
