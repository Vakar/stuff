package space.vakar.stuff.persistence.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.ServiceStuff;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceStuffImplTest {

  @Mock private Repository<Stuff> repository;

  @InjectMocks private ServiceStuff service = new ServiceStuffImpl();

  private static final int ONCE = 1;

  private Stuff stuff = new Stuff(
          1, "stuff_one_name", "noname", "stuff_one_description", new BigDecimal("10.1"), null);
  private List<Stuff> stuffList = Collections.singletonList(stuff);

  @Test
  public void add() {
    service.add(stuff);
    verify(repository, times(ONCE)).add(stuff);
  }

  @Test
  public void testAdd() {
    service.add(stuffList);
    verify(repository, times(ONCE)).add(stuffList);
  }

  @Test
  public void update() {
    stuff.setName("stuff_one_new_name");
    stuff.setCost(new BigDecimal("10.13"));
    service.update(stuff);
    verify(repository, times(ONCE)).update(stuff);
  }

  @Test
  public void remove() {
    service.remove(stuff);
    verify(repository, times(ONCE)).remove(stuff);
  }

  @Test
  public void testRemove() {
    service.remove(stuff.getId());
    Hql hql = new HqlRemoveById(Stuff.class, stuff.getId());
    verify(repository, times(ONCE)).remove(hql);
  }

  @Test
  public void readById() {
    Hql hql = new HqlGetById(Stuff.class, stuff.getId());
    when(repository.query(hql)).thenReturn(stuffList);
    service.readById(stuff.getId());
    verify(repository, times(ONCE)).query(hql);
  }

  @Test
  public void readAll() {
    Hql hql = new HqlGetAll(Stuff.class);
    service.readAll();
    verify(repository, times(ONCE)).query(hql);
  }

  @Test
  public void findStuffByUserIdTest(){
    int userId = 1;
    Hql hql = new HqlFindByFieldValue(Stuff.class, ServiceStuffImpl.FIELD_USER_ID, String.valueOf(userId));
    when(repository.query(hql)).thenReturn(stuffList);
    List<Stuff> actualStuffList = service.findStuffByUserId(userId);
    verify(repository, times(ONCE)).query(hql);
    assertEquals(stuffList, actualStuffList);
  }
}
