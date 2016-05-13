/* - IFKitDetachListener - 
 * Here we display the status, which will be false as the device is not attached.
 * We will also clear the display fields and hide the inputs and outputs.
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package rfid;

import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;


public class RFIDDetachListener implements DetachListener{
    

	public void detached(DetachEvent ae) {
		System.out.println("detachment of " + ae);
	}
    
}
