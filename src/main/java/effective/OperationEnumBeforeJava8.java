package effective;

public enum OperationEnumBeforeJava8 {

    ADD {
        @Override
        public int compute(int x, int y) {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        public int compute(int x, int y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        public int compute(int x, int y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public int compute(int x, int y) {
            return x / y;
        }
    };

    public abstract int compute(int x, int y);

    public static void main(String[] args) {
        System.out.println(MULTIPLY.compute(5, 5));
    }
}
