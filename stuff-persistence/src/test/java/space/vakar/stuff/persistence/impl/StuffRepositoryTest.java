package space.vakar.stuff.persistence.impl;

import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.tools.RunScript;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class StuffRepositoryTest extends DatabaseTestConfig {

  private Repository<Stuff> repositoryStuff = new RepositoryStuff();
  private DataFileLoader loader = new FlatXmlDataFileLoader();

  private static final String TABLE_NAME = "STUFF";

  private static final String SCHEMA_FILE = "classpath:schema.sql";

  private static final String DATASET_FOLDER = "/datasets/stuff";
  private static final String START_DATASET = DATASET_FOLDER + "/start.xml";
  private static final String CREATE_DATASET = DATASET_FOLDER + "/create.xml";
  private static final String UPDATE_DATASET = DATASET_FOLDER + "/update.xml";
  private static final String DELETE_DATASET = DATASET_FOLDER + "/delete.xml";

  private Stuff stuffOne = new Stuff(1, "stuff_one_name", new BigDecimal("10.1"));
  private Stuff stuffTwo = new Stuff(2, "stuff_two_name", new BigDecimal("20"));

  public StuffRepositoryTest(String name) {
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
    repositoryStuff.add(stuffTwo);
    Assertion.assertEquals(getExpectedTable(CREATE_DATASET), getActualTable());
  }

//  public void testAddCollection() throws Exception {
//    List<Stuff> list = Collections.singletonList(stuffTwo);
//    repositoryStuff.add(list);
//    Assertion.assertEquals(getExpectedTable(CREATE_DATASET), getActualTable());
//  }

  public void testQueryById() {
    List<Stuff> stuffList = repositoryStuff.query(new HqlGetById(Stuff.class, stuffOne.getId()));
    Stuff actualStuff = stuffList.get(0);
    assertEquals(stuffOne, actualStuff);
  }

  public void testQueryAll(){
    List<Stuff> stuffList = repositoryStuff.query(new HqlGetAll(Stuff.class));
    Stuff actualStuff = stuffList.get(0);
    assertEquals(stuffOne, actualStuff);
  }

  public void testUpdate() throws Exception {
    stuffOne.setName("stuff_one_new_name");
    stuffOne.setCost(new BigDecimal("22.13"));
    repositoryStuff.update(stuffOne);
    Assertion.assertEquals(getExpectedTable(UPDATE_DATASET), getActualTable());
  }

  public void testRemove() throws Exception {
    repositoryStuff.remove(stuffOne);
    Assertion.assertEquals(getExpectedTable(DELETE_DATASET), getActualTable());
  }

  public void testRemoveById() throws Exception {
    repositoryStuff.remove(new HqlRemoveById(Stuff.class, stuffOne.getId()));
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
