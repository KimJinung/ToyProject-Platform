package kimjinung.platform.exception;

public class NotEnoughStockEx extends RuntimeException{

    public NotEnoughStockEx(String message) {
        super(message);
    }
}
