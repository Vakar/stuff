package space.vakar.stuff.email;

public class EmailException extends RuntimeException {

  /**
   * Parametrised constructor for EmailException class.
   *
   * @param message explanation message
   * @param cause cause exception
   */
  public EmailException(String message, Throwable cause) {
    super(message, cause);
  }
}
