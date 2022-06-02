package lambdastart;

public class Main {
    public static void main(String[] args) {
        FuncVoidNoParam f0 = () -> System.out.println("Hello lambda!");
        f0.printHello();

        FuncVoidOneParam f1_1 = input -> System.out.println("Hello," + input);
        f1_1.printHello("Super lambda!");
        FuncVoidOneParam f1_2 = (String input) -> {
            System.out.println("yet another implementation of the same method");
            System.out.println("Hi," + input);
        };
        f1_2.printHello("Super lambda!");

        FuncVoidTwoParam add = (a, b) -> System.out.println(a + b);
        FuncVoidTwoParam sub = (a, b) -> System.out.println(a - b);
        add.calculate(1, 2);
        sub.calculate(3, 2);

        FuncIntTwoParam add2 = (a, b) -> {
            System.out.println("a = " + a + ", b = " + b);
            return a + b;
        };
        FuncIntTwoParam sub2 = (a, b) -> a - b;
        add2.calc(2, 3);
        sub2.calc(3, 2);

        FuncVoidTwoParam anonClass = new FuncVoidTwoParam() {
            @Override
            public void calculate(int a, int b) {
                System.out.println(a + b);
            }
        };
        anonClass.calculate(1, 2);
    }
}
