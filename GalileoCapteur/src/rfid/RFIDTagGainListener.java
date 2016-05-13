
package rfid;

import java.util.*;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagGainEvent;

public class RFIDTagGainListener extends Observable implements TagGainListener {
    
	public RFIDTagGainListener()
	{
		super();
	}
	
	public void tagGained(TagGainEvent oe)
	{
		this.setChanged();
		this.notifyObservers(oe);
		//lala=(RFIDPhidget)oe.getSource();
		//MyCollector.sendEvent();
	}
    
}
