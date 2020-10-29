package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 2)
@Warmup(iterations = 5)
public class FoldedLog {

    @Benchmark
    public double foldedLog() {
        int x = 8;
        return Math.log(x); // folds to  return 2.0794415416798357;
    }

    @State(Scope.Benchmark)
    public static class Log {
        public int x = 8;
    }

    @Benchmark
    public double log(Log input) {
        return Math.log(input.x);
    }
}
