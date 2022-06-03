package consumer_supplier;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printHi = in -> System.out.println("Hi, " + in);
        Consumer<String> book = in -> System.out.printf("Here's your %s's book\n", in);
        printHi.andThen(book).accept("Abigail");


        Supplier<Integer> getRandom = () -> {
            Random rnd = new Random();
            return rnd.nextInt(Integer.MAX_VALUE);
        };

        Consumer<Integer> multiplyByRandom = in -> {
            // Random rnd = new Random();
            // int mul = rnd.nextInt(Integer.MAX_VALUE/in);
            int mul = getRandom.get()/in;
            System.out.printf("%d * %d = %d", in, mul, in*mul);
        };
        multiplyByRandom.andThen(in -> System.out.println("\n")).accept(11);

        Supplier<String> getDayOfWeek = () -> LocalDate.now().getDayOfWeek().toString();
        Supplier<Integer> getDayOfMonth = () -> LocalDate.now().getDayOfMonth();
        Supplier<Integer> getDayOfYear = () -> LocalDate.now().getDayOfYear();
        System.out.println("day of week: "+ getDayOfWeek.get());
        System.out.println("day of month: " + getDayOfMonth.get());
        System.out.println("day of year: " + getDayOfYear.get());

    }
}
