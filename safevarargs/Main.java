package safevarargs;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sum of 1, 3, 5, 7, 9: " + sum(1, 3, 5, 7, 9));

        List<String> l1 = Arrays.asList("string1");
        List<String> l2 = Arrays.asList("string2");
        List<String> l3 = Arrays.asList("string3");
        printFirstElems(l1, l2, l3);

    }

    @SafeVarargs //to inform others that this method is safe to use
    private static void printFirstElems(List<String>... lists) {
        // Object[] array = lists;
        // List<Integer> list = Arrays.asList(1, 2, 3);
        // array[0] = list;
        // for (List<String> s: lists) {
        //     String ss = s.get(0); //!!!!!!!! - class cast exception
        //     System.out.println(ss);
        // }    
        for (List<String> s: lists) {
            String ss = s.get(0);
            System.out.println(ss);
        }    
    }

    private static int sum(int... numbers) {
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

}