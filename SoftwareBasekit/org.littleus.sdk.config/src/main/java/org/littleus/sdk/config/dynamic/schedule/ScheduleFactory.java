package org.littleus.sdk.config.dynamic.schedule;

import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.FixedDelayPollingScheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hyzhangj
 */
public enum ScheduleFactory {
    INSTANCE;
    private final Map<Long, AbstractPollingScheduler> scheduleContainer = new ConcurrentHashMap<>();

    public AbstractPollingScheduler getScheduler(int initialDelayMillis, int delayMillis) {
        Long periodKey = (long) initialDelayMillis << 32 + delayMillis;
        return scheduleContainer.computeIfAbsent(periodKey, key -> new FixedDelayPollingScheduler(initialDelayMillis, delayMillis, false));
    }

    public AbstractPollingScheduler getScheduler() {
        Long periodKey = -1L;
        return scheduleContainer.computeIfAbsent(periodKey, key -> new FixedDelayPollingScheduler());
    }
}
