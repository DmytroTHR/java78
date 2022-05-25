package rethrowex;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        int rnd = (int) (Math.random() * 10);
        try {
            testException(rnd);
        } catch (FirstException ex) {
            LOGGER.log(Level.WARNING, "First exception: " + getSuppressedString(ex));
        } catch (SecondException ex) {
            LOGGER.log(Level.WARNING, "Second exception: " + getSuppressedString(ex));
        }
    }

    private static String getSuppressedString(Throwable t) {
        StringBuilder sb = new StringBuilder();
        for (Throwable suppressed : t.getSuppressed()) {
            sb.append(suppressed.getMessage()).append("\n");
        }
        return sb.toString();
    }

    public static void testException(int choice) throws FirstException, SecondException {
        try {
            if (choice <= 5) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception ex) {
            ex.addSuppressed(new Exception("choice: " + choice));
            throw ex;
        }

    }

    static class FirstException extends Exception {
    }

    static class SecondException extends Exception {
    }

}