package function;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        Function<String, String> convertStr = input -> input.toUpperCase();
        System.out.println(convertStr.apply("function"));
        UnaryOperator<String> convertStr2 = input -> input.toUpperCase();
        System.out.println(convertStr2.apply("same with unary operator"));

        Function<String, String> sameValue = Function.identity();
        System.out.println("Given value: " + sameValue.apply("hello"));

        Function<Integer, Integer> multiply = a -> {
            System.out.println("Double operation");
            return a * 2;
        };
        multiply = multiply.andThen(a -> {
            System.out.println("And then triple operation");
            return a * 3;
        });
        System.out.println("double andThen triple value of 3: " + multiply.apply(3));

        Function<Integer, Integer> divide = a -> {
            System.out.println("Divide by 2 operation");
            return a / 2;
        };
        UnaryOperator<Integer> divideBy3 = a -> {
            System.out.println("Triple operation");
            return a / 3;
        };
        divide = divide.compose(divideBy3);
        System.out.println("div by 2 compose div by 3 value of 2: " + divide.apply(30));
    }
}
