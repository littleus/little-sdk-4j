package org.littleus.sdk.toolkit.string;

/**
 * 字符串工具.
 *
 * @author hyzhangj
 */
public class StringToolkit {
    /**
     * 快速分割（字符中不包含分隔符）.
     *
     * @param str            目标字符串
     * @param targetIndexAsc 待分割下标（升序）
     * @param split          分隔符
     * @return the string
     */
    public static String[] quickSpilt(String str, int[] targetIndexAsc, char split) {
        int targetLength = targetIndexAsc.length;
        String[] result = new String[targetIndexAsc[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        char[] chars = str.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            // 不是分隔符，跳过
            if (chars[i] != split) {
                continue;
            }

            // 遇到分隔符
            // 1. 若是需要提取的字段，则提取添加到结果集中
            if (splitNo++ == targetIndexAsc[curTargetIndex]) {
                final int len = i - lastStartIndex;
                if (len > 0) {
                    final char[] sub = new char[len];
                    System.arraycopy(chars, lastStartIndex, sub, 0, len);
                    result[targetIndexAsc[curTargetIndex]] = new String(sub);
                } else {
                    result[targetIndexAsc[curTargetIndex]] = "";
                }
                // 已经获取到最后一个目标字段了，剩下的不再分割
                if (++curTargetIndex == targetLength) {
                    break;
                }
            }
            // 2. 如果不是是需要提取的字段，刷新开始下标
            lastStartIndex = i + 1;
        }

        return result;
    }
}
