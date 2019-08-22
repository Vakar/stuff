package vakar.space.stuff.ui.struts2.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StuffListMockitoTestCase {

  @Mock RepositoryService<Stuff> service;
  @InjectMocks StuffList action = new StuffList();

  @Test
  public void serviceDoTest() {
    Stuff stuff = new Stuff(0, "", BigDecimal.ZERO);
    when(service.readAll()).thenReturn(Collections.singletonList(stuff));
    action.execute();
    verify(service, times(1)).readAll();
  }
}
