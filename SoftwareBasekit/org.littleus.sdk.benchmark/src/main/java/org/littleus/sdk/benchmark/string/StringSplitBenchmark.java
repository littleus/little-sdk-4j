package org.littleus.sdk.benchmark.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * @author hyzhangj
 * @since 2022 -11-11
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
public class StringSplitBenchmark {
    private static final String TARGET = "|a|bbb|c|123|||||fjjf|jkajdfl|fjaahds|cz,hg|fjdasldfj|fdal|gadl|fadfasf|gafg|gadga|gadga|gadga|gaga|gag|gag|gag|ggagg|gagdga|gadgasg|gadgadgasag|Ggaasd|gdgsdgG|gsgsg|GSDGDS|Ggsdgsg|gsdg|gsdsd|gdsdgG|gsggS|gsg|SGS|G|GS|GS|GS|SD|DF|S|DG|S|DG|DG|SD|G|SD|G|SDG|S|DG|D|G|S|DG|SDG|G|DS|G|D|G|S|G|DS|G||SGSD|F|DS|D|F||F||F|S||||F|SD||DF|F|D|D|F|DS|FS|G|DG|G|DS||G|SD|G|SD||||||||||||||||||||||||GDS|F|D|||F|SD||F|SDF|DFFSFDDSDFD|||FSDF|S|F||DF|SDF|DF|DFDSDDFDSF|S|F|FS|F|DF||S||F||SD|F|SDF||S|DF|SD|G|DG|SDG|SD||G|SD|G|DSG||S||WR|E|W|F|FD|SF|D|F|";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RunnerException the runner exception
     */
    public static void main(String[] args) throws RunnerException {
        new StringSplitBenchmark().test_user_split();
        System.out.println(1);

        Options options = new OptionsBuilder().include(StringSplitBenchmark.class.getSimpleName()).build();
        new Runner(options).run();
    }

    @Benchmark
    public void test_jdk_split() {
        TARGET.split("\\|", -1);
    }

    @Benchmark
    public void test_user_split() {
        int[] targetSeqNo = new int[]{1, 3, 5, 7, 23, 66, 70, 77, 87, 89, 100, 101, 111, 120, 123, 124, 156, 160, 173,
                175};
        String[] split = split(TARGET, targetSeqNo);
        int length = split.length;
    }

    private static String[] split(String target, int[] targetSeqNo) {
        int targetLength = targetSeqNo.length;
        String[] result = new String[targetLength];

        int lastStartIndex = 0;
        int curSeqNo = 0;
        int curTargetIndex = 0;
        char[] chars = target.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
            if (ch == '|') {
                if (++curSeqNo == targetSeqNo[curTargetIndex]) {
                    result[curTargetIndex++] = new String(chars, lastStartIndex, i - lastStartIndex);
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