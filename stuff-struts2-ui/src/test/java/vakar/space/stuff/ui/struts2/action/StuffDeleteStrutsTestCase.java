package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsTestCase;

public class StuffDeleteStrutsTestCase extends StrutsTestCase {

  private static final String ACTION_PATH = "/stuffDelete.action";

  public void testExecutePasses() throws Exception {
    request.addParameter("id", "1");
    ActionProxy actionProxy = getActionProxy(ACTION_PATH);
    StuffDelete action = (StuffDelete) actionProxy.getAction();
    assertNotNull("The action is null but should not be.", action);
    action.setService(MockStuffService.MOCK);
    String result = actionProxy.execute();
    assertEquals(
        "The execute method did not return " + ActionSupport.SUCCESS + " but should have.",
        ActionSupport.SUCCESS,
        result);
  }
}
