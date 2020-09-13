package hu.otemplom.karbantarto.model.Exceptions.User;

public class InvalidPasswordException extends Throwable {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
