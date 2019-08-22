package vakar.space.stuff.ui.struts2.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StuffSaveMockitoTestCase {

  @Mock RepositoryService<Stuff> service;
  @InjectMocks StuffSave action = new StuffSave();

  @Test
  public void serviceDoTest() {
    String name = "stuffName";
    String cost = "10.1";
    action.setName(name);
    action.setCost(cost);
    action.execute();
    Stuff stuff = new Stuff(name, new BigDecimal(cost));
    verify(service, times(1)).add(stuff);
  }
}
