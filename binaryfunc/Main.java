package binaryfunc;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public class Main {
    private static final String HELLO = "Hello";
    public static void main(String[] args) {
        BinaryOperator<Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> mul = (a, b) -> a * b;
        System.out.println(add.apply(4, 2));
        System.out.println(mul.apply(4, 2));

        BinaryOperator<Integer> maxOp = BinaryOperator.maxBy((a,b)->a-b);
        System.out.println(maxOp.apply(4, 2));

        BinaryOperator<Integer> minOp = BinaryOperator.minBy((a,b)->a-b);
        System.out.println(minOp.apply(4, 5));

        BiPredicate<Integer, Integer> isEven = (a, b) -> a % 2 == 0;
        System.out.printf("2 and 3 even:%b\n", isEven.test(2,3));
        BiPredicate<String, Integer> example = (a, b) -> a.length() == b;
        System.out.printf("Length of '%s' is 5:%b\n", HELLO, example.test(HELLO, 5));

        BiConsumer<String, Integer> print = (a, b) -> System.out.printf("%s has %d characters\n", a, b);
        print.accept(HELLO, HELLO.length());
        
    }
}
