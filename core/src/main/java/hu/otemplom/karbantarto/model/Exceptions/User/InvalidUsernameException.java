package hu.otemplom.karbantarto.model.Exceptions.User;

public class InvalidUsernameException extends Throwable {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
