package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

public class StuffUpdateStrutsTestCase extends StrutsTestCase {

  private static final String ACTION_PATH = "/stuffUpdate.action";

  @Test
  public void testExecutePasses() throws Exception {
    request.addParameter("id", "1");
    request.addParameter("name", "stuff_name");
    request.addParameter("cost", "10");
    ActionProxy actionProxy = getActionProxy(ACTION_PATH);
    StuffUpdate action = (StuffUpdate) actionProxy.getAction();
    assertNotNull("The action is null but should not be.", action);
    action.setService(MockStuffService.MOCK);
    String result = actionProxy.execute();
    assertEquals(
        "The execute method did not return " + ActionSupport.SUCCESS + " but should have.",
        ActionSupport.SUCCESS,
        result);
  }
}
