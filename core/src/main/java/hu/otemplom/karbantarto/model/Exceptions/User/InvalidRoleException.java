package hu.otemplom.karbantarto.model.Exceptions.User;

public class InvalidRoleException extends Throwable{
    public InvalidRoleException(String message) {
        super(message);
    }
}
