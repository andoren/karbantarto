package hu.otemplom.karbantarto.service.Exceptions.UserService;

public class DuplicateUserException extends Throwable {
    public DuplicateUserException() {
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
