package streamfilters;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numbers.stream()
                .filter(Predicate.not(num -> (num / 10) % 2 == 0))
                .forEach(System.out::println);
        System.out.println("==========");

        Supplier<Integer> rnd100 = () -> new Random().nextInt(100);
        Stream.generate(rnd100)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("==========");

        Stream.iterate(1, n -> n * 2)
                .skip(5)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("==========");

        List<Integer> someMoreNums = Arrays.asList(11, 12, 11, 2, 12, 25, 3, 6);
        someMoreNums.stream()
                .distinct()
                .forEach(print -> System.out.printf("%s | ", print));
        System.out.println("\n==========");

        someMoreNums.stream()
                .sorted()
                .forEach(print -> System.out.printf("%s | ", print));
        System.out.println("\n==========");

        someMoreNums.stream().filter(n -> n > 10)
                .peek(p -> System.out.printf("*%s | ", p))
                .sorted()
                .peek(p -> System.out.printf("+%s | ", p))
                .forEach(p -> System.out.print("<   "));
        System.out.println();

    }
}
