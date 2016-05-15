
package capteurs.rfid;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;

public class RFIDAttachListener implements AttachListener {
    

	public void attached(AttachEvent ae)
	{
		try
		{
			((RFIDPhidget)ae.getSource()).setAntennaOn(true);
		}
		catch (PhidgetException ex) { }
		System.out.println("attachment of " + ae);
	}
    
}
