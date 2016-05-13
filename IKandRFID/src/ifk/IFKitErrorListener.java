/* - IFKitErrorListener - 
 * Display the details of the error in a message box
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package ifk;

import com.phidgets.event.ErrorListener;
import com.phidgets.event.ErrorEvent;


public class IFKitErrorListener implements ErrorListener{

    public void error(ErrorEvent ae)
    {
    	System.out.println(ae.toString() +  "InterfaceKit Error Event");
    }
    
}
