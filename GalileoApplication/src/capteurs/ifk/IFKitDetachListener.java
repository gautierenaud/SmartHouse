/* - IFKitDetachListener - 
 * Here we display the status, which will be false as the device is not attached.
 * We will also clear the display fields and hide the inputs and outputs.
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package capteurs.ifk;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;


public class IFKitDetachListener implements DetachListener{
    

    public void detached(DetachEvent ae)
    {
         try
         {
            InterfaceKitPhidget detached = (InterfaceKitPhidget)ae.getSource();
            System.out.println(Boolean.toString(detached.isAttached()));
         }
         catch (PhidgetException ex)
         {
        	 System.out.println("Phidget error " + ex.getErrorNumber());
         }
    }
    
}
