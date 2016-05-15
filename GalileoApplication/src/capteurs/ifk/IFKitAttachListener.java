/* - IFKitAttachListener -
 * Here we'll display the interface kit details as well as determine how many output
 * and input fields to display as well as determine the range of values for 
 * the output simulator slider
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package capteurs.ifk;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;

public class IFKitAttachListener implements AttachListener {
    

    public void attached(AttachEvent ae)
    {
        try {
            InterfaceKitPhidget attached = (InterfaceKitPhidget)ae.getSource();
            System.out.println(Boolean.toString(attached.isAttached()));
            System.out.println(attached.getDeviceName());
            System.out.println(Integer.toString(attached.getSerialNumber()));
            System.out.println(Integer.toString(attached.getDeviceVersion()));
            System.out.println(Integer.toString(attached.getInputCount()));
            System.out.println(Integer.toString(attached.getOutputCount()));
            System.out.println(Integer.toString(attached.getSensorCount()));
        }
        catch (PhidgetException ex)
        {
            System.out.println("Phidget error " + ex.getErrorNumber());
        }
    }
    
}
