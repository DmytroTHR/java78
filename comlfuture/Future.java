package comlfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Future {
    public static void main(String[] args) throws Exception {
        runAsync();
        supplyAsync();
        thenApply();
        thenAccept();
    }

    private static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> async = CompletableFuture.runAsync(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                        System.out.println("Hello from async method");
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                });
        System.out.println(">>Main thread (written after async)");
        async.get();
        System.out.println("After async");
    }

    private static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> async = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Hello from supplyAsync method";
                });
        System.out.println(">>Main thread (written after async)");
        String res = async.get();
        System.out.println("After async: " + res);
    }

    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "future 1";
                });
        System.out.println("middle block between future1 and future2");
        CompletableFuture<String> future2 = future1.thenApply(
                s -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return s + " then future 2";
                });
        System.out.println(">>Main thread (written after async)");
        String res = future2.get();
        System.out.println("After async: " + res);
    }

    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Some future";
                }).thenAccept(
                        f -> {
                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                throw new IllegalStateException(e);
                            }
                            System.out.println("Future: " + f);
                        });
        System.out.println(">>Main thread (written after async)");
        future.get();
    }
}
