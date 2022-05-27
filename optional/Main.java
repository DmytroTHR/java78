package optional;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        String[] str = new String[10];
        str[4] = "Bunkernyi Don-don";
        // System.out.println(str[5].toLowerCase()); // NullPointerException

        Optional<String> opt1 = Optional.of(str[4]); // NPE if null provided
        System.out.println(opt1.get());
        opt1.map(String::toUpperCase).ifPresent(System.out::println);
        opt1.filter(f->f.contains("don"));

        Optional<Optional<String>> opt3 = Optional.of(opt1);
        opt3.map(m -> m.map(String::toLowerCase)).ifPresent(System.out::println);
        opt3.flatMap(mapper -> mapper.map(String::toLowerCase)).ifPresent(System.out::println);
        
        Optional<String> opt2 = Optional.ofNullable(str[5]);
        System.out.println(opt2);
        opt2.ifPresent(System.out::println);
        System.out.println(opt2.orElse("No value"));

    }
}
