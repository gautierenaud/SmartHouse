/* - IFKitOutputChangeListener - 
 * Here we check or uncheck the corresponding output checkbox based on the
 * index of the output that generated the event
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package ifk;

import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.OutputChangeEvent;

public class IFKitOutputChangeListener implements OutputChangeListener{

	private boolean[] digital_Output;
	
	public IFKitOutputChangeListener(boolean[] digital_Output)
	{
		this.digital_Output = digital_Output;
	}
	
	
	public void outputChanged(OutputChangeEvent outputChangeEvent)
    {
    	try
    	{
	    	int indexInput = outputChangeEvent.getIndex();
	    	if(indexInput<IFK.ifk_digital_Count)
	    	{
		    	boolean value = outputChangeEvent.getState();
		    	synchronized(this.digital_Output)
		    	{
		    		this.digital_Output[indexInput] = value;
		    	}
		    	System.out.println("Digital output " + indexInput + " changed : " + value);
	    	}
	    	else
	    	{
	    		throw new IndexOutOfBoundsException("IndexOutOfBoundsException Doutput index : " + indexInput);
	    	}
    	}
    	catch(IndexOutOfBoundsException ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    
}
