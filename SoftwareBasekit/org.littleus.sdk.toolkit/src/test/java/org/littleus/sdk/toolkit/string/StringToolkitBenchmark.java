package org.littleus.sdk.toolkit.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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
    public void jdk_string_split() {
        TARGET.split("\\|", -1);
    }

    @Benchmark
    public void quick_split() {
        StringToolkit.quickSpilt(TARGET, new int[]{0, 1, 2, 3, 4}, '|');
    }

}