package streams;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TestInfiniteStreams {

    private final List<Integer> evenNumbersFrom0To10 = List.of(0, 2, 4, 6, 8, 10);
    private final List<Integer> oddNumbersFrom0To10 = List.of(1, 3, 5, 7, 9);

    @Test
    public void testIterateWithPredicatehasNext() {
        List<Integer> evenNumbers = Stream
                .iterate(0, n -> n <= 10, n -> n + 2)
                .collect(toList());

        assertThat(evenNumbers).isEqualTo(evenNumbersFrom0To10);
    }

    @Test
    public void testIterateWithLimit() {
        List<Integer> evenNumbers = IntStream
                .iterate(1, n -> n + 2)
                .limit(5)
                .boxed()
                .collect(toList());

        assertThat(evenNumbers).isEqualTo(oddNumbersFrom0To10);
    }

    @Test
    public void testIterateWithTakeWhile() {
        List<Integer> evenNumbers = Stream
                .iterate(1, n -> n + 2)
                .takeWhile(n -> n <= 10)
                .collect(toList());

        assertThat(evenNumbers).isEqualTo(oddNumbersFrom0To10);
    }

    @Test
    public void testGenerate() {
        List<Double> randoms = Stream.generate(Math::random)
                .limit(10)
                .collect(toList());

        randoms.forEach(System.out::println);

        assertThat(randoms).hasSize(10);
    }


    @Test
    public void testPythagoreanTriples() {

        List<double[]> pythagoreanTriples = IntStream.iterate(1, a -> a + 1)
                .boxed()
                .limit(10)
                .flatMap(a -> IntStream.iterate(a, b -> b + 1)
                        .limit(10 - a)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(triple -> triple[2] % 1 == 0)
                )
                .collect(toList());

        pythagoreanTriples.forEach(triple -> System.out.println(
                String.format("%d %d %d", (int) triple[0], (int) triple[1], (int) triple[2])));

        assertThat(pythagoreanTriples).containsExactlyElementsOf(List.of(
                new double[]{3, 4, 5},
                new double[]{6, 8, 10}));

    }

}
