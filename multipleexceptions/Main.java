package multipleexceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Integer[] arr = { 1, 2, 3, null };
        try {
            for (int i = 0; i <= arr.length; i++) {
                System.out.println(arr[i] * arr[i]);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            LOGGER.log(Level.SEVERE, "One of the exceptions caught: " + ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception caught: " + ex.getMessage());
        }
    }
}
