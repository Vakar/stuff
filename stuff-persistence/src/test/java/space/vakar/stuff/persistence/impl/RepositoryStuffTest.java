package space.vakar.stuff.persistence.impl;

import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.tools.RunScript;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.persistence.util.DatabaseTestConfig;
import space.vakar.stuff.persistence.util.PropertiesUtil;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

public class RepositoryStuffTest extends DatabaseTestConfig {

  private final Repository<Stuff> repositoryStuff = new RepositoryStuff();
  private final DataFileLoader loader = new FlatXmlDataFileLoader();

  private static final String TABLE_NAME = "STUFF";

  private static final String SCHEMA_FILE = "classpath:schema.sql";

  private static final String DATASET_FOLDER = "/datasets/stuff";
  private static final String START_DATASET = DATASET_FOLDER + "/start.xml";
  private static final String CREATE_DATASET = DATASET_FOLDER + "/create.xml";
  private static final String UPDATE_DATASET = DATASET_FOLDER + "/update.xml";
  private static final String DELETE_DATASET = DATASET_FOLDER + "/delete.xml";

  private static final int USER_ID = 1;
  private static final byte[] PICTURE_MOCK = "picture".getBytes();

  private final User owner = new User(1, "username", "user1@domain.com", "one");
  private final Stuff stuff = new Stuff.Builder()
          .id(1)
          .name("stuff_one_name")
          .brand("noname")
          .description("stuff_one_description")
          .cost(new BigDecimal("10.10"))
          .picture(PICTURE_MOCK)
          .commissionDate(new GregorianCalendar(1970, Calendar.JANUARY, 1))
          .user(owner)
          .build();
  private final Stuff stuff2 = new Stuff.Builder()
          .id(2)
          .name("stuff_two_name")
          .brand("brand_name")
          .description("stuff_two_description")
          .cost(new BigDecimal("20"))
          .picture(PICTURE_MOCK)
          .commissionDate(new GregorianCalendar(1970, Calendar.JANUARY, 2))
          .user(owner)
          .build();

  public RepositoryStuffTest(String name) {
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
    repositoryStuff.add(stuff2);
    Assertion.assertEquals(getExpectedTable(CREATE_DATASET), getActualTable());
  }

  public void testQueryById() {
    List<Stuff> stuffList = repositoryStuff.query(new HqlGetById(Stuff.class, stuff.getId()));
    Stuff actualStuff = stuffList.get(0);
    assertEquals(stuff, actualStuff);
  }

  public void testQueryAll() {
    List<Stuff> stuffList = repositoryStuff.query(new HqlGetAll(Stuff.class));
    Stuff actualStuff = stuffList.get(0);
    assertEquals(stuff, actualStuff);
  }

  public void testQueryByUserId() {
    Hql hql =
        new HqlFindByFieldValue(
            Stuff.class, ServiceStuffImpl.FIELD_USER_ID, String.valueOf(USER_ID));
    List<Stuff> stuffList = repositoryStuff.query(hql);
    assertEquals(Collections.singletonList(stuff), stuffList);
  }

  public void testUpdate() throws Exception {
    stuff.setName("stuff_one_new_name");
    stuff.setCost(new BigDecimal("22.13"));
    repositoryStuff.update(stuff);
    Assertion.assertEquals(getExpectedTable(UPDATE_DATASET), getActualTable());
  }

  public void testRemove() throws Exception {
    repositoryStuff.remove(stuff);
    Assertion.assertEquals(getExpectedTable(DELETE_DATASET), getActualTable());
  }

  public void testRemoveById() throws Exception {
    repositoryStuff.remove(new HqlRemoveById(Stuff.class, stuff.getId()));
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
