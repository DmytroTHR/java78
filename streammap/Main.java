package streammap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Spike");
        names.add("Дмитро");

        Stream<String> stream = names.stream();
        stream.map(name -> name.toUpperCase()).forEach(System.out::println);
        UnaryOperator<String> fnReverse = res -> {
            char[] rst = res.toCharArray();
            char tmp;
            for (int i = 0; i < rst.length / 2; i++) {
                tmp = rst[i];
                rst[i] = rst[rst.length - 1 - i];
                rst[rst.length - 1 - i] = tmp;
            }
            return new String(rst);
        };
        names.stream().map(fnReverse).forEach(System.out::println);

        String[] someWords = { "Hello", "World", "Java", "Streams" };
        Stream<String> streamWords = Arrays.stream(someWords);
        streamWords.map(word -> word.split("")).flatMap(Arrays::stream).forEach(System.out::println);
        List<List<String>> hardList = Arrays.asList(
                Arrays.asList("Hey"),
                Arrays.asList("Hoy"),
                Arrays.asList("Hoa"));
        hardList.stream().map(Collection::stream).forEach(System.out::println);
        hardList.stream().flatMap(Collection::stream).forEach(System.out::println);
    }
}