package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main extends RecursiveAction {

    private static final long threshold = 2;
    private volatile long number;

    public Main(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    private static long fib(long n) {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }

    @Override
    protected void compute() {
        long n = number;
        if (n <= threshold) {
            number = fib(n);
        } else {
            Main left = new Main(n - 1);
            Main right = new Main(n - 2);
            Main.invokeAll(left, right);
            number = left.getNumber() + right.getNumber();
        }
    }

    public static void main(String[] args) {
        Main task = new Main(33);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        System.out.println("Fibonacci of 33 is " + task.getNumber());
    }

}
