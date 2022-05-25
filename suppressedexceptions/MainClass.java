package suppressedexceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

    private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    public static void main(String[] args) {
        try{
            afterv7();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception caught: " + e.getMessage());
            for (final Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }

        try{
            beforev7();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Old exception: " + e.getMessage());
        }
    }

    private static void afterv7() throws Exception {
        try (MyClass mc = new MyClass();) {
            mc.doSomething();
        } 
    }

    private static void beforev7() throws Exception {
        MyClass mc = new MyClass();
        try {
            mc.doSomething();
        } finally {
            mc.close();
        }
    }
    
}
