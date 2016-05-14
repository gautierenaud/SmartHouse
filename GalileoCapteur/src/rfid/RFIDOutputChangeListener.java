/* - IFKitOutputChangeListener - 
 * Here we check or uncheck the corresponding output checkbox based on the
 * index of the output that generated the event
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package rfid;

import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.OutputChangeEvent;

public class RFIDOutputChangeListener implements OutputChangeListener{

	public void outputChanged(OutputChangeEvent oe)
	{
		//System.out.println(oe);
	}
    
}
