package training.task.dao.api.exception;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable e) {
        super(e);
    }

    public ConnectionPoolException(String message, Throwable e) {
        super(message, e);
    }
}
