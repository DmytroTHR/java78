package objectsclass;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Person p = null;
        Person p1 = new Person("Vasya");
        Person p2 = new Person("Vasya");

        if (Objects.equals(p1, p2)) {
            System.out.println("p1 and p2 are equal");
        } else {
            System.out.println("p1 and p2 are not equal");
        }

        printPersonName(p);
    }

    private static void printPersonName(Person p) {
        Objects.requireNonNull(p, "Person should not be null. Your own NPE text is here.\n");
        System.out.println(p.getName());
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Person person = (Person) o;
            return Objects.equals(name, person.getName());
        }
    }
}