package defaultmethod;

public interface Vehicle {
    public void drive();

    public void stop();

    default void honk() { // no need to implement
        System.out.println("Honk!");
    }
}
