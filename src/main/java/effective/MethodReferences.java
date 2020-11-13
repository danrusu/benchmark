package effective;

import java.time.Instant;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferences {

    static Function<Integer, Integer> f1 = MethodReferences::addOne;
    static Function<Integer, Integer> f2 = f -> f + 2;

    public static void main(String[] args) {

        // 1.
        st(s -> Integer.parseInt(s));
        st(Integer::parseInt);

        // 2. 
        bound(instant -> Instant.now().isAfter(instant));
        bound(Instant.now()::isAfter);

        // 3.
        unbound(s -> s.toLowerCase());
        unbound(String::toLowerCase);

        // 4. 
        constructor(() -> new TreeMap<>());
        constructor(TreeMap::new);

        // 5. 
        array(i -> new int[i]);
        array(int[]::new);

    }

    public static Integer addOne(Integer integer) {
        return integer + 1;
    }

    public static final Instant NOW = Instant.now();

    public static Instant getNow() {
        return NOW;
    }

    public static void st(Function<String, Integer> function) {
    }

    public static void bound(Predicate<Instant> predicate) {
    }

    public static void unbound(UnaryOperator<String> operator) {
    }

    public static void constructor(Supplier<TreeMap<String, Integer>> supplier) {
    }

    public static void array(Function<Integer, int[]> function) {
    }
}
