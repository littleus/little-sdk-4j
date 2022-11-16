package org.littleus.sdk.toolkit.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type String toolkit test.
 */
class StringToolkitTest {

    /**
     * Split.
     */
    @Test
    void split() {
        String target = "|fdsakm|a|fds|bc|";
        int[] indexes = new int[]{0, 2, 3, 4};
        String[] result = StringToolkit.quickSpilt(target, indexes, '|');

        Assertions.assertTrue(result.length > 0);
    }
}