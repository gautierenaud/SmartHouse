/* - IFKitInputChangeListener -
 * Here we check or uncheck the corresponding input checkbox based on the
 * index of the digital input that generated the event
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package ifk;

import com.phidgets.event.InputChangeListener;
import com.phidgets.event.InputChangeEvent;

public class IFKitInputChangeListener implements InputChangeListener
{
	private boolean[] digital_Input;
	
	public IFKitInputChangeListener(boolean[] digital_Input)
	{
		this.digital_Input = digital_Input;
	}
	
    public void inputChanged(InputChangeEvent inputChangeEvent)
    {
    	try
    	{
	    	int indexInput = inputChangeEvent.getIndex();
	    	if(indexInput<IFK.ifk_digital_Count)
	    	{
		    	boolean value = inputChangeEvent.getState();
		    	this.digital_Input[indexInput] = value;
		    	//System.out.println("Digital input " + indexInput + " changed : " + value);
	    	}
	    	else
	    	{
	    		throw new IndexOutOfBoundsException("IndexOutOfBoundsException Dinput index : " + indexInput);
	    	}
    	}
    	catch(IndexOutOfBoundsException ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
}
