package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsTestCase;

public class StuffEditStrutsTestCase extends StrutsTestCase {

  private static final String ACTION_PATH = "/stuffEditPage.action";

  public void testExecutePasses() throws Exception {
    request.addParameter("id", "1");
    ActionProxy actionProxy = getActionProxy(ACTION_PATH);
    StuffEdit action = (StuffEdit) actionProxy.getAction();
    assertNotNull("The action is null but should not be.", action);
    action.setService(MockStuffService.MOCK);
    String result = actionProxy.execute();
    assertEquals(
        "The execute method did not return " + ActionSupport.SUCCESS + " but should have.",
        ActionSupport.SUCCESS,
        result);
  }
}
