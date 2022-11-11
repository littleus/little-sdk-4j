package org.littleus.sdk.toolkit.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringToolkitTest {

    @Test
    void split() {
        String target = "|fdsakm|a|fds|bc|";
        int[] indexes = new int[]{0, 2, 3, 4};
        String[] result = StringToolkit.split(target, indexes, '|');
        
        Assertions.assertTrue(result.length > 0);
    }
}