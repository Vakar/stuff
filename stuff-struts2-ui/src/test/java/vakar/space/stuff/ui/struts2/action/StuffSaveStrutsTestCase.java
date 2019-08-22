package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsTestCase;

public class StuffSaveStrutsTestCase extends StrutsTestCase {

  private static final String ACTION_PATH = "/stuffSave.action";

  public void testExecutePasses() throws Exception {
    request.addParameter("name", "stuff_name");
    request.addParameter("cost", "10");
    ActionProxy actionProxy = getActionProxy(ACTION_PATH);
    StuffSave action = (StuffSave) actionProxy.getAction();
    assertNotNull("The action is null but should not be.", action);
    action.setService(MockStuffService.MOCK);
    String result = actionProxy.execute();
    assertEquals(
        "The execute method did not return " + ActionSupport.SUCCESS + " but should have.",
        ActionSupport.SUCCESS,
        result);
  }
}
