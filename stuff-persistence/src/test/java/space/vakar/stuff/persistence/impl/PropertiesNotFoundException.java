package space.vakar.stuff.persistence.impl;

public class PropertiesNotFoundException extends RuntimeException {
  public PropertiesNotFoundException(String message, Throwable exception) {
    super(message, exception);
  }
}
