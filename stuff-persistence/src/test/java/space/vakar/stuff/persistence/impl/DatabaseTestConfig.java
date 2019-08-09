package space.vakar.stuff.persistence.impl;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.h2.H2DataTypeFactory;

import java.util.Properties;

public abstract class DatabaseTestConfig extends DBTestCase {

  public DatabaseTestConfig(String name) {
    super(name);
    Properties properties =
        PropertiesUtil.readPropertiesFromFile(PropertiesUtil.CONNECTION_PROPERTIES_FILE);
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
        properties.getProperty(PropertiesUtil.CONNECTION_DRIVER_PROPERTY_NAME));
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
        properties.getProperty(PropertiesUtil.CONNECTION_URL_PROPERTY_NAME));
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
        properties.getProperty(PropertiesUtil.CONNECTION_USER_PROPERTY_NAME));
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
        properties.getProperty(PropertiesUtil.CONNECTION_PASS_PROPERTY_NAME));
  }

  @Override
  protected void setUpDatabaseConfig(DatabaseConfig config) {
    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
  }
}
