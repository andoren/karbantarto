package hu.otemplom.karbantarto.service.Exceptions.UserService;

public class UserDoesNotExistsException extends Throwable {
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
