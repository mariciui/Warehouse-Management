package BLL;

public class InsufficientQuantityException extends Throwable {
    public InsufficientQuantityException() {
        super("Insufficient Quantity");
    }
}
