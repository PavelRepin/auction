package training.task.model.api.exception;

public class ValidationServiceException extends ServiceException {
    public ValidationServiceException() {
        super();
    }

    public ValidationServiceException(String msg) {
        super(msg);
    }

    public ValidationServiceException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidationServiceException(Throwable e) {
        super(e);
    }
}