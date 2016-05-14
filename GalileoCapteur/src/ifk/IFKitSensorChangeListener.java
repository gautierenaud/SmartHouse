/* - IFKitSensorChangeListener - 
 * Set the textbox content based on the input index that is communicating
 * with the interface kit
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package ifk;


import com.phidgets.event.SensorChangeListener;
import com.phidgets.event.SensorChangeEvent;
/**
 *
 * @author Owner
 */
public class IFKitSensorChangeListener implements SensorChangeListener{

	private int[] analog_Input;
	
	public IFKitSensorChangeListener(int[] analog_Input)
	{
		this.analog_Input = analog_Input;
	}
	
    public void sensorChanged(SensorChangeEvent sensorChangeEvent)
    {
    	try
    	{
	    	int indexInput = sensorChangeEvent.getIndex();
	    	if(indexInput<IFK.ifk_analog_Count)
	    	{
		    	int value = sensorChangeEvent.getValue();
		    	synchronized(this.analog_Input)
		    	{
		    		this.analog_Input[indexInput] = value;
		    	}
		    	if(indexInput==0) //pressure
		    	{
		    		int maxPressure = IFK.getMaxPressure();
		    		if(value > maxPressure)
		    		{
		    			IFK.setMaxPressure(value);
		    		}
		    	}
		    	else if(indexInput==1) //IR
		    	{
		    		int maxIR = IFK.getMaxInfrared();
		    		if(value > maxIR)
		    		{
		    			IFK.setMaxInfrared(value);
		    		}
		    	}
		    	//System.out.println("Analog input " + indexInput + " changed : " + value);
	    	}
	    	else
	    	{
	    		throw new IndexOutOfBoundsException("IndexOutOfBoundsException Ainput index : " + indexInput);
	    	}
    	}
    	catch(IndexOutOfBoundsException ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
}
