package defaultmethod;

public class Car implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Car is driving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping");
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
        car.stop();
        car.honk(); // honk() is not implemented but can be used
    }

}
