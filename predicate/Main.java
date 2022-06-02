package predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven = i -> i % 2 == 0;
        System.out.printf("2 is even:%b\n", isEven.test(2));

        Predicate<Integer> isGreaterThan10 = i -> i > 10;
        System.out.printf("9 is greater than 10:%b\n", isGreaterThan10.test(9));

        System.out.printf("12 is even & greater than 10:%b\n", isEven.and(isGreaterThan10).test(12));
        System.out.printf("13 is even || greater than 10:%b\n", isEven.or(isGreaterThan10).test(13));
        System.out.println("12 is odd: " + isEven.negate().test(12));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenOnly = list.stream().filter(isEven).collect(Collectors.toList());
        System.out.println("Even numbers from 1 to 10: " + evenOnly);

        Predicate<String> equalHello = Predicate.isEqual("Hello");
        System.out.println("Is 'hello' equal to 'Hello'? " + equalHello.test("hello"));
    }
}
