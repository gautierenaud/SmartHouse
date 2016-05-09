package toulouse.insa.smartcontrol.communicate;

import frameself.Dispatcher;
import frameself.format.Action;

/**
 * Created by gautierenaud on 09/05/16.
 */

// to receive and redirect (dispatch) actions from frameself
public class SpecificDispatcher {

    public static Dispatcher dispatcher = new Dispatcher("192.168.150.4", 6000, 7000);

    public static void start(){
        Action action = dispatcher.receive();
        System.out.println(action.getName()+" excuted");
        // Update the action result and error attributes
        action.setResult("true");
        action.setError("No error");
        // Send back the action to FRAMSELF
        dispatcher.send(action);

    }
}
