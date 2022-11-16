package org.littleus.sdk.toolkit.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * The type String toolkit benchmark.
 *
 * @author hyzhangj
 * @since 2022 -11-16
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class StringToolkitBenchmark {

    private static final String TARGET = "a||bcdddd|fdsafas|fadffs||hskhsajhdfj|";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RunnerException the runner exception
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(StringToolkitBenchmark.class.getSimpleName()).build();
        new Runner(options).run();
    }

    @Benchmark
    public void quick_split0() {
        TARGET.split("\\|", -1);
    }

    @Benchmark
    public void quick_split() {
        quickSpilt(TARGET, new int[]{0, 1, 2, 3, 4, 5, 6}, '|');
    }

    @Benchmark
    public void quick_split1() {
        quickSpilt1(TARGET, new int[]{0, 1, 2, 3, 4, 5, 6}, '|');
    }

    @Benchmark
    public void quick_split2() {
        quickSpilt2(TARGET, new int[]{0, 1, 2, 3, 4, 5, 6}, '|');
    }

    @Benchmark
    public void quick_split3() {
        quickSpilt3(TARGET, new int[]{0, 1, 2, 3, 4, 5, 6}, '|');
    }

    @Benchmark
    public void quick_split4() {
        quickSpilt4(TARGET, new int[]{0, 1, 2, 3, 4, 5, 6}, '|');
    }

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
                char[] sub = Arrays.copyOfRange(chars, lastStartIndex, i);
                result[targetIndexAsc[curTargetIndex++]] = new String(sub);
                // 已经获取到最后一个目标字段了，剩下的不再分割
                if (curTargetIndex == targetLength) {
                    break;
                }
            }
            // 2. 如果不是是需要提取的字段，刷新开始下标
            lastStartIndex = i + 1;
        }

        return result;
    }

    /**
     * 快速分割（字符中不包含分隔符）.
     *
     * @param str            目标字符串
     * @param targetIndexAsc 待分割下标（升序）
     * @param split          分隔符
     * @return the string
     */
    public static String[] quickSpilt1(String str, int[] targetIndexAsc, char split) {
        int targetLength = targetIndexAsc.length;
        String[] result = new String[targetIndexAsc[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            // 不是分隔符，跳过
            if (str.charAt(i) != split) {
                continue;
            }

            // 遇到分隔符
            // 1. 若是需要提取的字段，则提取添加到结果集中
            if (splitNo++ == targetIndexAsc[curTargetIndex]) {
                char[] sub = new char[i - lastStartIndex];
                for (int subIdx = 0, idx = lastStartIndex; idx < i; idx++) {
                    sub[subIdx++] = str.charAt(idx);
                }
                result[targetIndexAsc[curTargetIndex++]] = new String(sub);
                // 已经获取到最后一个目标字段了，剩下的不再分割
                if (curTargetIndex == targetLength) {
                    break;
                }
            }
            // 2. 如果不是是需要提取的字段，刷新开始下标
            lastStartIndex = i + 1;
        }

        return result;
    }

    public static String[] quickSpilt2(String str, int[] targetIndexAsc, char split) {
        int targetLength = targetIndexAsc.length;
        String[] result = new String[targetIndexAsc[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        final char[] chars = str.toCharArray();
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
                final char[] sub = new char[len];
                System.arraycopy(chars, lastStartIndex, sub, 0, len);
                result[targetIndexAsc[curTargetIndex++]] = new String(sub);
                // 已经获取到最后一个目标字段了，剩下的不再分割
                if (curTargetIndex == targetLength) {
                    break;
                }
            }
            // 2. 如果不是是需要提取的字段，刷新开始下标
            lastStartIndex = i + 1;
        }

        return result;
    }

    public static String[] quickSpilt3(String str, int[] targetIndexAsc, char split) {
        int targetLength = targetIndexAsc.length;
        String[] result = new String[targetIndexAsc[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        final char[] chars = str.toCharArray();
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

    private static Field field = getField();

    private static Field getField() {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            return value;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] quickSpilt4(String str, int[] targetIndexAsc, char split) {
        int targetLength = targetIndexAsc.length;
        String[] result = new String[targetIndexAsc[targetLength - 1] + 1];

        int lastStartIndex = 0;
        int splitNo = 0;
        int curTargetIndex = 0;
        char[] chars;
        try {
            chars = (char[]) field.get(str);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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