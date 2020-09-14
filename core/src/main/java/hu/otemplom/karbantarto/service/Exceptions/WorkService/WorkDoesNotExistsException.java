package hu.otemplom.karbantarto.service.Exceptions.WorkService;

public class WorkDoesNotExistsException extends Throwable {
    public WorkDoesNotExistsException(String message) {
        super(message);
    }
}
