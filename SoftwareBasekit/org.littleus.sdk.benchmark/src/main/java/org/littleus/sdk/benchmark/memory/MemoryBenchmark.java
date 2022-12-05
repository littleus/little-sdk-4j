package org.littleus.sdk.benchmark.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author hyzhangj
 */
public class MemoryBenchmark {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance("").toPrintable());
        System.out.println(ClassLayout.parseInstance(new String("")).toPrintable());

        System.out.println(ClassLayout.parseInstance("1").toPrintable());
        System.out.println(ClassLayout.parseInstance(new String("1")).toPrintable());

        System.out.println(ClassLayout.parseInstance(new char[]{'1', '2', '3', '4'}).toPrintable());
    }
}
