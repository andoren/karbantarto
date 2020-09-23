package hu.otemplom.karbantarto.controller.exceptions;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String message) {
        super(message);
    }
}
