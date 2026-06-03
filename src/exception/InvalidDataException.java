package exception;

/**
 * Custom exception used to handle invalid data in the system.
 */

public class InvalidDataException extends Exception {

    public InvalidDataException(String message) {
        super(message);
    }
}