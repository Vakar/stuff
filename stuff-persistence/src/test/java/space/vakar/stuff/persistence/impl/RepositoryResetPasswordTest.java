package space.vakar.stuff.persistence.impl;

import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.tools.RunScript;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.persistence.util.DatabaseTestConfig;
import space.vakar.stuff.persistence.util.PropertiesUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class RepositoryResetPasswordTest extends DatabaseTestConfig {

  private Repository<ResetPassword> repository = new RepositoryResetPassword();
  private DataFileLoader loader = new FlatXmlDataFileLoader();

  private static final String TABLE_NAME = "RESET_PASSWORD";
  private static final String USER_ID_FIELD = "user";

  private static final String SCHEMA_FILE = "classpath:schema.sql";

  private static final String DATASET_FOLDER = "/datasets/password_recovery";
  private static final String START_DATASET = DATASET_FOLDER + "/start.xml";
  private static final String CREATE_DATASET = DATASET_FOLDER + "/create.xml";
  private static final String DELETE_DATASET = DATASET_FOLDER + "/delete.xml";

  private static final int USER_ID = 1;
  private static final String UUID_1 = "123e4567-e89b-42d3-a456-556642440000";
  private static final String UUID_2 = "639b4940-9320-47b7-95d4-376f7dc368e6";
  private User user = new User(USER_ID, "username", "user1@domain.com", "one");
  private ResetPassword token1 = new ResetPassword(UUID_1, user);
  private ResetPassword token2 = new ResetPassword(UUID_2, user);

  public RepositoryResetPasswordTest(String name) {
    super(name);
  }

  @Override
  protected void setUp() throws Exception {
    Properties properties =
        PropertiesUtil.readPropertiesFromFile(PropertiesUtil.CONNECTION_PROPERTIES_FILE);
    String dbUrl = properties.getProperty(PropertiesUtil.CONNECTION_URL_PROPERTY_NAME);
    String user = properties.getProperty(PropertiesUtil.CONNECTION_USER_PROPERTY_NAME);
    String pass = properties.getProperty(PropertiesUtil.CONNECTION_PASS_PROPERTY_NAME);
    RunScript.execute(dbUrl, user, pass, SCHEMA_FILE, StandardCharsets.UTF_8, false);
    super.setUp();
  }

  @Override
  protected IDataSet getDataSet() {
    return loader.load(START_DATASET);
  }

  public void testAdd() throws Exception {
    repository.add(token2);
    Assertion.assertEquals(getExpectedTable(CREATE_DATASET), getActualTable());
  }

  public void testRemove() throws Exception {
    repository.remove(token1);
    Assertion.assertEquals(getExpectedTable(DELETE_DATASET), getActualTable());
  }

  public void testQueryById() {
    Hql hql = new HqlFindByFieldValue(ResetPassword.class, "id", token1.getId());
    System.out.println(UUID.randomUUID().toString().length());
    List<ResetPassword> tokenList = repository.query(hql);
    ResetPassword actualToken = tokenList.get(0);
    System.out.println(actualToken);
    assertEquals(token1, actualToken);
  }

  public void testQueryByUser(){
    List<ResetPassword> expectedTokens = Collections.singletonList(token1);
    Hql hql = new HqlFindByFieldValue(ResetPassword.class, USER_ID_FIELD, String.valueOf(1));
    List<ResetPassword> actualTokens = repository.query(hql);
    assertEquals(expectedTokens, actualTokens);
  }

  private ITable getActualTable() throws Exception {
    IDataSet databaseDataSet = getConnection().createDataSet();
    return databaseDataSet.getTable(TABLE_NAME);
  }

  private ITable getExpectedTable(String datasetPath) throws DataSetException {
    IDataSet expectedDataSet = loader.load(datasetPath);
    return expectedDataSet.getTable(TABLE_NAME);
  }
}
