package space.vakar.stuff.email;

public class EmailException extends RuntimeException {
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
