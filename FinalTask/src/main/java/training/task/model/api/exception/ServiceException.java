package training.task.model.api.exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable ex) {
        super(ex);
    }

    public ServiceException(String message, Throwable ex) {
        super(message, ex);
    }
}
