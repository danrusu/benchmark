package streams;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TestFlatMap {

    @Test
    public void testExtractingDistinctLetters() {
        List<String> words = asList("the quick brown fox jumps over the lazy dog");
        List expectedLetters = Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        );

        List<String> distinctLetters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .filter(letter -> !letter.equals(" "))
                .sorted()
                .collect(toList());

        assertThat(distinctLetters).isEqualTo(expectedLetters);
    }

    @Test
    public void testListsCombining() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // combine lists to obtain
        List expectedCombined = Arrays.asList(
                new int[]{1, 3}, new int[]{1, 4},
                new int[]{2, 3}, new int[]{2, 4},
                new int[]{3, 3}, new int[]{3, 4});

        List<int[]> combined = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                        .map(n2 -> new int[]{n1, n2})
                )
                .collect(toList());

        assertThat(combined).containsExactlyElementsOf(expectedCombined);
    }

    @Test
    public void testEmptyStreamFlatMapping() {
        String first = "first";
        String second = null;
        String third = "third";

        List<String> expected = List.of(first, third);

        Stream elements = Stream.of(
                Stream.of(first),
                Stream.ofNullable(second),
                Stream.of(third),
                Stream.empty()
        );


        List<String> all = (List<String>) elements
                .flatMap(Function.identity())
                .collect(toList());

        assertThat(all).isEqualTo(expected);
    }

    @Test
    public void testValidProperties() {
        System.setProperty("test1", "test1");
        System.setProperty("test3", "test3");

        List<String> propertiesNames = Stream.of("test1", "test2", "test3", "test4")
                .map(System::getProperty)
                .flatMap(Stream::ofNullable)
                .collect(toList());

        assertThat(propertiesNames).isEqualTo(List.of("test1", "test3"));
    }

}
