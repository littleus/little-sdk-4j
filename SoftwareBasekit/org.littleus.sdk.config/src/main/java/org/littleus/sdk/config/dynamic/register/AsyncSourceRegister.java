package org.littleus.sdk.config.dynamic.register;

import org.littleus.sdk.config.dynamic.dependency.ISourceDependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author hyzhangj
 */
public class AsyncSourceRegister {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSourceRegister.class);
    private static final int WAIT_MILLIS_PERIOD = 1000;

    public static void register(ISourceDependency dependency, Runnable regAction) {
        CompletableFuture.runAsync(() -> waitReady(dependency)).thenRunAsync(regAction);
    }

    private static void waitReady(ISourceDependency dependency) {
        long t1 = System.currentTimeMillis();
        while (!dependency.isReady()) {
            waitMillis(WAIT_MILLIS_PERIOD);
        }
        long t2 = System.currentTimeMillis();
        LOGGER.info("wait ready end, cost: {} ms, name: {}", t2 - t1, dependency.getClass().getSimpleName());
    }

    private static void waitMillis(int waitMillis) {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
