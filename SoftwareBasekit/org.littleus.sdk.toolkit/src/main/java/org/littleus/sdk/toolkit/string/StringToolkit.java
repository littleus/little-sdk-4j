package org.littleus.sdk.toolkit.string;

import java.util.Arrays;

/**
 * The type String toolkit.
 *
 * @author hyzhangj
 */
public class StringToolkit {

    /**
     * Split string [ ].
     *
     * @param str         the str
     * @param targetIndex the target index
     * @return the string [ ]
     */
    public static String[] split(String str, int[] targetIndex, char split) {
        int targetLength = targetIndex.length;
        String[] result = new String[targetIndex[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        char[] chars = str.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (chars[i] == split) {
                if (splitNo++ == targetIndex[curTargetIndex]) {
                    char[] sub = Arrays.copyOfRange(chars, lastStartIndex, i);
                    result[targetIndex[curTargetIndex++]] = new String(sub);
                    if (curTargetIndex == targetLength) {
                        break;
                    }
                }
                lastStartIndex = i + 1;
            }
        }

        return result;
    }
}
