package staticmethod;

public class Hello implements IHello{
    public static int Multiply(int a, int b) {
        return a * b*2;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World from class!");
        Hello h = new Hello();
        // h.Sum(5, 6); //possible but not recommended
        int mulCl = Hello.Multiply(5, 6);
        System.out.println("Class 5 * 6 = " + mulCl);

        IHello.main(args);
        int mul = IHello.Multiply(5, 6);
        System.out.println("Interface 5 * 6 = " + mul);
    }
}
