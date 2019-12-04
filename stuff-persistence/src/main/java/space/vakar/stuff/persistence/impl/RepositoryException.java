package space.vakar.stuff.persistence.impl;

public class RepositoryException extends RuntimeException {

  /**
   * Current module exception.
   *
   * @param message exception message
   * @param cause cause of exception
   */
  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }
}
