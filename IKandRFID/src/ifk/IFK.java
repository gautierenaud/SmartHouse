package ifk;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;



public class IFK  {
    
    public static final int ifk_digital_Count = 8;
	public static final int ifk_analog_Count = 8;
    private boolean[] ifk_digital_Output;
    private boolean[] ifk_digital_Input;
    private int[] ifk_analog_Input;
    private InterfaceKitPhidget ifkph;
    private IFKitAttachListener ifk_attach_listener;
    private IFKitDetachListener ifk_detach_listener;
    private IFKitErrorListener ifk_error_listener;
    private IFKitInputChangeListener ifk_input_listener;
    private IFKitOutputChangeListener ifk_output_listener;
    private IFKitSensorChangeListener ifk_sensor_listener;
    
    
    public IFK() 
    {
    	ifk_digital_Output = new boolean[ifk_digital_Count];
    	ifk_digital_Input = new boolean[ifk_digital_Count];
    	ifk_analog_Input = new int[ifk_analog_Count];
    	for(int i=0;i<ifk_digital_Count;i++)
    	{
    		ifk_digital_Output[i] = false;
    		ifk_digital_Input[i] = false;
    	}
    	for(int i=0;i<ifk_analog_Count;i++)
    	{
    		ifk_analog_Input[i] = 0;
    	}
        try 
        {
        	ifkph = new InterfaceKitPhidget();
            System.out.println("waiting for IFK attachment...");
            ifk_attach_listener = new IFKitAttachListener();
            ifk_detach_listener = new IFKitDetachListener();
            ifk_error_listener = new IFKitErrorListener();
            ifk_input_listener = new IFKitInputChangeListener(ifk_digital_Input);
            ifk_output_listener = new IFKitOutputChangeListener(ifk_digital_Output);
            ifk_sensor_listener = new IFKitSensorChangeListener(ifk_analog_Input);
            ifkph.addAttachListener(ifk_attach_listener);
            ifkph.addDetachListener(ifk_detach_listener);
            ifkph.addErrorListener(ifk_error_listener);
            ifkph.addInputChangeListener(ifk_input_listener);
            ifkph.addOutputChangeListener(ifk_output_listener);
            ifkph.addSensorChangeListener(ifk_sensor_listener);
            ifkph.openAny();
        } 
        catch (PhidgetException ex) 
        {
            System.out.println("IFK Phidget Error " + ex.getErrorNumber());
            System.exit(-1);
        }
        System.out.println("IFK opened sucessfully.");
    }
    
    public boolean[] getDigitalOutputs()
    {
    	return this.ifk_digital_Output;
    }
    
    public void dispose() {
    	
        try
        {
        	ifkph.removeSensorChangeListener(ifk_sensor_listener);
        	ifkph.removeOutputChangeListener(ifk_output_listener);
        	ifkph.removeInputChangeListener(ifk_input_listener);
        	ifkph.removeErrorListener(ifk_error_listener);
        	ifkph.removeDetachListener(ifk_detach_listener);
        	ifkph.removeAttachListener(ifk_attach_listener);
        	ifkph.close();
        	ifkph = null;
        }
        catch(PhidgetException ex)
        {
            System.out.println("IFK Phidget Error " + ex.getErrorNumber());
        }
    }
    
    public void setDigitalOutput(int index, boolean value)
    {
    	try 
    	{
    		ifkph.setOutputState(index, value);
		} 
    	catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

}