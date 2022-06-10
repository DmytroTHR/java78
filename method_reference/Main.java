package method_reference;

import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class Main {
    public static void main(String[] args) {
        ToIntFunction<String> lengthLambda = i -> i.length();
        ToIntFunction<String> lengthInstanceMethod = String::length;
        ToIntFunction<String> lengthStaticMethod = Main::length;
        Main obj = new Main();
        ToIntFunction<String> lengthObjMethod = obj::lengthObj;

        BiPredicate<Integer, Integer> areEqual = (i1, i2) -> i1 == i2;
        boolean tst1 = areEqual.test(lengthLambda.applyAsInt("value"), lengthInstanceMethod.applyAsInt("value"));
        boolean tst2 = areEqual.test(lengthInstanceMethod.applyAsInt("value"), lengthStaticMethod.applyAsInt("value"));
        System.out.println("Test result: " + (tst1 == tst2));
        System.out.println("Example - 'value' length: " + lengthObjMethod.applyAsInt("value"));

        FuncInterface sc1 = (a, b) -> new SomeClass(a, b);
        FuncInterface sc2 = SomeClass::new;
        SomeClass some1 = sc1.createSC(1, "one");
        SomeClass some2 = sc2.createSC(2, "two");
        System.out.println(some1);
        System.out.println(some2);
    }

    public static int length(String s) {
        return s.length();
    }

    public int lengthObj(String s) {
        return s.length();
    }

}
