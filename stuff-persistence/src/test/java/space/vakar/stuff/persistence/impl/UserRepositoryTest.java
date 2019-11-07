package space.vakar.stuff.persistence.impl;

import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.tools.RunScript;
import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.impl.hql.HqlFindByFieldValue;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class UserRepositoryTest extends DatabaseTestConfig {

  private Repository<User> repositoryUser = new RepositoryUser();
  private DataFileLoader loader = new FlatXmlDataFileLoader();

  private static final String TABLE_NAME = "APP_USER";

  private static final String FIELD_USER_NAME = "userName";

  private static final String SCHEMA_FILE = "classpath:schema.sql";

  private static final String DATASET_FOLDER = "/datasets/user";
  private static final String START_DATASET = DATASET_FOLDER + "/start.xml";
  private static final String CREATE_DATASET = DATASET_FOLDER + "/create.xml";
  private static final String UPDATE_DATASET = DATASET_FOLDER + "/update.xml";
  private static final String DELETE_DATASET = DATASET_FOLDER + "/delete.xml";

  private static final String USER_ONE_NAME = "user1";
  private static final String USERNAME_NOT_USED = "usernameNotUsed";
  private static final String NEW_USER_EMAIL = "user1_new@domain.com";

  private User userOne = new User(1, USER_ONE_NAME, "user1@domain.com", "one");
  private User userTwo = new User(2, "user2", "user2@domain.com", "two");

  public UserRepositoryTest(String name) {
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
    repositoryUser.add(userTwo);
    Assertion.assertEquals(getExpectedTable(CREATE_DATASET), getActualTable());
  }

  public void testQueryById() {
    List<User> userList = repositoryUser.query(new HqlGetById(User.class, userOne.getId()));
    User actualUser = userList.get(0);
    assertEquals(userOne, actualUser);
  }

  public void testQueryAll() {
    List<User> userList = repositoryUser.query(new HqlGetAll(User.class));
    User actualUser = userList.get(0);
    assertEquals(userOne, actualUser);
  }

  public void testFindByFieldValue_WhenValueExist(){
    Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_NAME, USER_ONE_NAME);
    List<User> users = repositoryUser.query(findByFieldValue);
    assertEquals(1, users.size());
    assertEquals(userOne, users.get(0));
  }

  public void testFindByFieldValue_WhenValueDoesNotExist(){
    Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_NAME, USERNAME_NOT_USED);
    List<User> users = repositoryUser.query(findByFieldValue);
    assertTrue(users.isEmpty());
  }

  public void testUpdate() throws Exception {
    userOne.setEmail(NEW_USER_EMAIL);
    repositoryUser.update(userOne);
    Assertion.assertEquals(getExpectedTable(UPDATE_DATASET), getActualTable());
  }

  public void testRemove() throws Exception {
    repositoryUser.remove(userOne);
    Assertion.assertEquals(getExpectedTable(DELETE_DATASET), getActualTable());
  }

  public void testRemoveById() throws Exception {
    repositoryUser.remove(new HqlRemoveById(User.class, userOne.getId()));
    Assertion.assertEquals(getExpectedTable(DELETE_DATASET), getActualTable());
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
