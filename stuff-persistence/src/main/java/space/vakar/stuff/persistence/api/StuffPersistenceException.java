package space.vakar.stuff.persistence.api;

public class StuffPersistenceException extends RuntimeException{
  public StuffPersistenceException(String message, Throwable exception) {
    super(message, exception);
  }
}
