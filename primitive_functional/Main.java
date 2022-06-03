package primitive_functional;

import java.time.Instant;
import java.util.Date;
import java.util.function.DoubleConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongSupplier;
import java.util.function.ToLongBiFunction;

public class Main {
    public static void main(String[] args) {
        IntPredicate isOdd = i -> i % 2 == 1;
        DoubleConsumer print = d -> System.out.println(d);
        IntToDoubleFunction square = i -> i * i;
        System.out.println(isOdd.test(2));
        print.accept(square.applyAsDouble(2));
        ToLongBiFunction<Integer, Integer> mul = (a, b) -> (long)a * (long)b;
        System.out.println(mul.applyAsLong(Integer.MAX_VALUE, 111));
        LongSupplier getTime = () -> System.currentTimeMillis();
        System.out.println(Date.from(Instant.ofEpochMilli(getTime.getAsLong())));
    }
}
