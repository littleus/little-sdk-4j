package org.littleus.sdk.config.dynamic.dependency;

import org.springframework.stereotype.Component;

/**
 * @author hyzhangj
 */
@Component
public class NonDenpendency implements ISourceDependency {
    @Override
    public boolean isReady() {
        return true;
    }
}
