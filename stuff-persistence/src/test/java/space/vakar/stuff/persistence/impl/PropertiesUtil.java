package space.vakar.stuff.persistence.impl;

import java.util.Properties;

class PropertiesUtil {

  static final String CONNECTION_PROPERTIES_FILE = "/connection.properties";
  static final String CONNECTION_DRIVER_PROPERTY_NAME = "driver";
  static final String CONNECTION_URL_PROPERTY_NAME = "url";
  static final String CONNECTION_USER_PROPERTY_NAME = "user";
  static final String CONNECTION_PASS_PROPERTY_NAME = "pass";

  private PropertiesUtil() {}

  static Properties readPropertiesFromFile(String fileName) {
    Properties properties = new Properties();
    try {
      properties.load(PropertiesUtil.class.getResourceAsStream(fileName));
    } catch (Exception e) {
      throw new PropertiesNotFoundException(e.getMessage(), e);
    }
    return properties;
  }
}
