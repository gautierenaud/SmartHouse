package rfid;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;

import frameselfcom.ThreadFrameself;

public class RFID {

    private RFIDPhidget rfidph;
    private RFIDAttachListener rfid_attach_listener;
    private RFIDDetachListener rfid_detach_listener;
    private RFIDErrorListener rfid_error_listener;
    private RFIDOutputChangeListener rfid_output_listener;
    private RFIDTagGainListener rfid_gain_listener;
    private RFIDTagLossListener rfid_loss_listener;
    
    public RFID(ThreadFrameself thread_frameself)
    {
    	try
        {
    		System.out.println("waiting for RFID attachment...");
    		rfidph = new RFIDPhidget();
            rfid_attach_listener = new RFIDAttachListener();
            rfid_detach_listener = new RFIDDetachListener();
            rfid_error_listener = new RFIDErrorListener();
            rfid_gain_listener = new RFIDTagGainListener();
            rfid_loss_listener = new RFIDTagLossListener();
            rfid_output_listener = new RFIDOutputChangeListener();
            rfid_gain_listener.addObserver(thread_frameself);
            rfid_loss_listener.addObserver(thread_frameself);
            rfidph.addAttachListener(rfid_attach_listener);
            rfidph.addDetachListener(rfid_detach_listener);
            rfidph.addErrorListener(rfid_error_listener);
            rfidph.addTagGainListener(rfid_gain_listener);
            rfidph.addTagLossListener(rfid_loss_listener);
            rfidph.addOutputChangeListener(rfid_output_listener);
            rfidph.openAny();
            
        }
        catch(PhidgetException ex)
        {
            System.out.println("RFID Phidget Error " + ex.getErrorNumber());
            System.exit(-1);
        }
    	System.out.println("RFID opened sucessfully.");
    }
    
    public void dispose() 
    {
    	try
        {
    		rfidph.removeTagLossListener(rfid_loss_listener);
    		rfidph.removeOutputChangeListener(rfid_output_listener);
    		rfidph.removeTagGainListener(rfid_gain_listener);
    		rfidph.removeErrorListener(rfid_error_listener);
    		rfidph.removeDetachListener(rfid_detach_listener);
    		rfidph.removeAttachListener(rfid_attach_listener);
    		rfidph.close();
    		rfidph = null;
        }
        catch(PhidgetException ex)
        {
            System.out.println("RFID Phidget Error " + ex.getErrorNumber());
        }
    }
}
