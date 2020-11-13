package effective;

import java.util.function.IntBinaryOperator;

public enum OperationEnumAfterJava8 {

    ADD((x, y) -> x + y),
    SUBTRACT((x, y) -> x - y),
    MULTIPLY((x, y) -> x * y),
    DIVIDE((x, y) -> x / y);

    private final IntBinaryOperator operator;

    OperationEnumAfterJava8(IntBinaryOperator operation) {
        this.operator = operation;
    }

    public int compute(int x, int y) {
        return operator.applyAsInt(x, y);
    }

    public static void main(String[] args) {
        System.out.println(MULTIPLY.compute(5, 5));
    }

}
