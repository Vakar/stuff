package vakar.space.stuff.ui.struts2.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StuffEditMockitoTestCase {

  @Mock RepositoryService<Stuff> service;
  @InjectMocks StuffEdit action = new StuffEdit();

  @Test
  public void serviceDoTest() {
    int id = 1;
    Stuff stuff = new Stuff(0, "", BigDecimal.ZERO);
    when(service.readById(id)).thenReturn(stuff);
    action.setId(id);
    action.execute();
    verify(service, times(1)).readById(id);
  }
}
