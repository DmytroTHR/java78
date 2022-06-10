package annotate;

import java.util.ArrayList;

class Parent {
    public void method1() {
        System.out.println("Parent method1");
    }

    @Deprecated(since = "2")
    public void method2() {
        System.out.println("Parent method2");
    }
}

public class GeneralPurpose extends Parent {

    @Override // to control method signature
    public void method1() {
        System.out.println("Child method1");
    }

    public static void main(String[] args) {
        Parent p = new GeneralPurpose();
        p.method1();
        p.method2();

        @SuppressWarnings("unused") // not to show warning
        Parent notused = new Parent();

        @SuppressWarnings({ "rawtypes", "unused" }) // not to show warning
        // @SuppressWarnings("all")
        ArrayList aList = new ArrayList();

        Box<String> box = new @ReadOnly @NonEmpty Box<>(11, "hi");
        box.new @ReadOnly NestedBox(12, "something");
    }
}
