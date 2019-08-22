package vakar.space.stuff.ui.struts2.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.model.Stuff;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StuffDeleteMockitoTestCase {

  @Mock RepositoryService<Stuff> service;
  @InjectMocks StuffDelete action = new StuffDelete();

  @Test
  public void serviceDoTest() {
    int id = 1;
    action.setId(id);
    action.execute();
    verify(service, times(1)).remove(id);
  }
}
