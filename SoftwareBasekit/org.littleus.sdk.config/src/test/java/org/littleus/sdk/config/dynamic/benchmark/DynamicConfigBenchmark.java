package org.littleus.sdk.config.dynamic.benchmark;

import com.netflix.config.DynamicProperty;
import com.netflix.config.DynamicPropertyFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * The type Dynamic config util benchmark.
 *
 * @author hyzhangj
 * @since 2022 -09-25
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public class DynamicConfigBenchmark {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RunnerException the runner exception
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(DynamicConfigBenchmark.class.getSimpleName()).build();
        new Runner(options).run();
    }

    /**
     * Gets double using dynamic property.
     * <p>
     * 248215174.515 ± 22085267.691  ops/s
     */
    @Benchmark
    public void getDoubleUsingDynamicProperty() {
        DynamicProperty.getInstance("double.value").getDouble();
    }

    /**
     * Gets double with default using dynamic property.
     * <p>
     * 248498289.676 ± 38113673.583  ops/s
     */
    @Benchmark
    public void getDoubleWithDefaultUsingDynamicProperty() {
        DynamicProperty.getInstance("double.value").getDouble(1d);
    }

    /**
     * Gets double with default using dynamic property factory.
     * <p>
     * 35359982.122 ±  1281432.155  ops/s
     */
    @Benchmark
    public void getDoubleWithDefaultUsingDynamicPropertyFactory() {
        DynamicPropertyFactory.getInstance().getDoubleProperty("double.value", 1d).get();
    }
}