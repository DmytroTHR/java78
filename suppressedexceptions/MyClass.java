package suppressedexceptions;

public class MyClass implements AutoCloseable {

    public void doSomething() throws Exception {
        throw new RuntimeException("MyClass.doSomething() got IOException");
    }

    public void close() throws Exception {
        throw new NullPointerException("Exception thrown from close()");
    }

}
