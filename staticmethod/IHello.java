package staticmethod;

public interface IHello { //WOW this is a interface that can run the program
    public static void main(String[] args) {
        System.out.println("Hello, World from interface!");
    }

    static int Multiply(int a, int b) {
        return a * b;
    }
}
