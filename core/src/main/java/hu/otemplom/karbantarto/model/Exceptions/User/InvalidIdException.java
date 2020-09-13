package hu.otemplom.karbantarto.model.Exceptions.User;

public class InvalidIdException extends Throwable {
    public InvalidIdException(String message) {
        super(message);
    }
}
