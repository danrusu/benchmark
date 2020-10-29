package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 2)
public class DeadCodeElimination {

    @Benchmark
    @Warmup(iterations = 5) //default is 20
    public void doNothing() {
    }

    @Benchmark
    @Warmup(iterations = 10)
    public void objectCreation() {
        new Object(); // dead code
    }

    @Benchmark
    @Warmup(iterations = 10)
    public Object pillarsOfCreation() {
        return new Object();
    }

    @Benchmark
    @Warmup(iterations = 5)
    public void blackHole(Blackhole blackhole) {
        blackhole.consume(new Object());
    }
}
