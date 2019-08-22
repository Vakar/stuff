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
public class StuffUpdateMockitoTestCase {

  @Mock RepositoryService<Stuff> service;
  @InjectMocks StuffUpdate action = new StuffUpdate();

  @Test
  public void serviceDoTest() {
    int id = 1;
    String name = "stuffName";
    String cost = "10.1";
    action.setId(id);
    action.setName(name);
    action.setCost(cost);
    action.execute();
    Stuff stuff = new Stuff(id, name, new BigDecimal(cost));
    verify(service, times(1)).update(stuff);
  }
}
