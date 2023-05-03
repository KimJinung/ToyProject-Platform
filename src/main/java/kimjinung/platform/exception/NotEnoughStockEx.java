package kimjinung.platform.exception;

public class NotEnoughStockEx extends RuntimeException{
    public NotEnoughStockEx() {
    }

    public NotEnoughStockEx(String message) {
        super(message);
    }

    public NotEnoughStockEx(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockEx(Throwable cause) {
        super(cause);
    }

}
