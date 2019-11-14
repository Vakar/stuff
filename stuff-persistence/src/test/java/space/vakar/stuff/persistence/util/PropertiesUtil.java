package space.vakar.stuff.persistence.util;

import java.util.Properties;

public class PropertiesUtil {

  public static final String CONNECTION_PROPERTIES_FILE = "/connection.properties";
  public static final String CONNECTION_DRIVER_PROPERTY_NAME = "driver";
  public static final String CONNECTION_URL_PROPERTY_NAME = "url";
  public static final String CONNECTION_USER_PROPERTY_NAME = "user";
  public static final String CONNECTION_PASS_PROPERTY_NAME = "pass";

  private PropertiesUtil() {}

  public static Properties readPropertiesFromFile(String fileName) {
    Properties properties = new Properties();
    try {
      properties.load(PropertiesUtil.class.getResourceAsStream(fileName));
    } catch (Exception e) {
      throw new PropertiesNotFoundException(e.getMessage(), e);
    }
    return properties;
  }
}
