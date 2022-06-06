package streamterminal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Stream.iterate(1, n -> n + 1)
                        .filter(n -> n % 2 == 0)
                        .limit(10)
                        .reduce(0, (a, b) -> a + b));

        System.out.println(
                Stream.generate(() -> new Random().nextInt(100))
                        .limit(5)
                        .peek(p -> System.out.printf("*%s | ", p))
                        .max(Integer::compare));

        List<String> names = Arrays.asList("John", "Paul", "John", "Paul");
        String oneBigName = names.stream().collect(Collectors.joining(" & "));
        System.out.println(oneBigName);
        Set<String> distinctNames = names.stream().collect(Collectors.toSet());
        System.out.println(distinctNames);

        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        String binarySum = numbers.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.reducing(0, Integer::sum),
                        Integer::toBinaryString));
        System.out.println(binarySum);

        Map<Integer, List<Integer>> oddityOfTens = numbers.stream()
                .collect(Collectors.groupingBy(n -> n / 10 % 2));
        System.out.println(oddityOfTens);

        Map<Boolean, List<Integer>> greater20 = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n > 20));
        System.out.println(greater20);

    }
}
