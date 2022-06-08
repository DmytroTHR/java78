package maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Maps {
    public static void main(String[] args) {
        Map<String, String> capitals = new HashMap<>();
        capitals.put("France", "Paris");
        capitals.put("Ukraine", "Kyiv");
        capitals.put("Germany", "Berlin");
        capitals.put("ruZZia", "Bunker");
        capitals.put("China", "Beijing");
        capitals.put("Poland", "Warsaw");

        capitals.forEach((key, value) -> System.out.println(key + " capital is " + value));

        capitals.entrySet().stream().sorted(Entry.comparingByKey()).forEachOrdered(System.out::println);
        capitals.entrySet().stream().sorted(Entry.comparingByValue()).forEachOrdered(System.out::println);
        System.out.println("Capital of orks: " + capitals.getOrDefault("russia", "jopa putina"));
        capitals.computeIfAbsent("Spain", name -> "Madrid");
        capitals.computeIfPresent("China", (k, v) -> "Lyang");
        capitals.compute("russia", (k, v) -> "Mordor");
        capitals.remove("russia", "Mordor");
        capitals.replaceAll((k, v) -> v.toUpperCase());
        capitals.replace("France", "Makrone");
        capitals.entrySet().stream().sorted(Entry.comparingByValue()).forEachOrdered(System.out::println);

    }
}
