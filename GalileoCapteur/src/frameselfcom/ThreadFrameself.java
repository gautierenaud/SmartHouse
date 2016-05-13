package frameselfcom;

import ifk.IFK;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import cexceptions.RFIDUserAlreadyFoundException;
import cexceptions.RFIDUserNotFoundException;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagLossEvent;

import java.util.*;
import rfid.RFID;
import rfid.RFIDTagGainListener;
import rfid.RFIDTagLossListener;

public class ThreadFrameself implements Runnable, Observer
{

    private boolean running_thread;
    private CollectorFrameself collframeself;
    private DispatcherFrameself disframeself;
    private ArrayList<String> rfid_present_tags;
    private ArrayList<String> rfid_goingto_tags;
	private IFK ifk;
	private RFID rfid;
	private int sleep_time;
	private static final String categoryHousePerson = "HousePerson";
	private static final String categoryHouseEmpty = "HouseEmpty";
	private static final String categoryProgramEnds = "ProgramEnds";
	private static final String categoryIR = "IRSensor";
	private static final String categoryPressure = "PressureSensor";
	private static final int houseTimeout = 20000;
	private Thread thread_dispatcher;
	private static final int timeoutThreadDispatcher = 5000;
	
    public ThreadFrameself(String ipAddressCollector, int portCollector, int sleep_time_thread)
    {
		System.out.println(Phidget.getLibraryVersion());
		rfid_present_tags = new ArrayList<String>();
		rfid_goingto_tags = new ArrayList<String>();
		ifk = new IFK();
		rfid = new RFID(this);
    	running_thread = true;
    	this.sleep_time = sleep_time_thread;
    	collframeself = new CollectorFrameself(ipAddressCollector, portCollector);
    	//disframeself = new DispatcherFrameself(this, ipAddressDispatcher, portSend, portListening);
    	//thread_dispatcher = new Thread(disframeself);
    	//thread_dispatcher.start();
    }
    
	public void run()
    {
    	while(running_thread)
    	{
    		synchronized(rfid_present_tags)
    		{
    			if(rfid_present_tags.isEmpty())
    			{
    				collframeself.sendEvent(categoryHouseEmpty, houseTimeout);
    			}
    			else
    			{
    				for(String id_person : rfid_present_tags)
    				{
    					collframeself.sendEvent(categoryHousePerson, id_person, houseTimeout);
    				}
    			}
    		}
    		System.out.println("IR : " + IFK.getMaxInfrared());
    		System.out.println("Pressure : " + IFK.getMaxPressure());
    		collframeself.sendEvent(categoryIR, String.valueOf(IFK.getMaxInfrared()), houseTimeout);
    		collframeself.sendEvent(categoryPressure, String.valueOf(IFK.getMaxPressure()), houseTimeout);
	    	try {
				Thread.sleep(sleep_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
	
	public void stopRunning()
	{
		running_thread = false;
		/*disframeself.stopRunning();
		try {
			thread_dispatcher.join(timeoutThreadDispatcher);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(thread_dispatcher.isAlive())
		{
			System.out.println("Killing thread dispatcher...");
			collframeself.sendEvent(categoryProgramEnds, houseTimeout);
			try {
				thread_dispatcher.join(timeoutThreadDispatcher);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(thread_dispatcher.isAlive())
			{
				try {
					long tid = thread_dispatcher.getId();
					String command = "kill -9 " + thread_dispatcher.getId();
					System.out.println(command);
					Runtime.getRuntime().exec(command);
				} 
				 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(!thread_dispatcher.isAlive())
			{
				System.out.println("Thread dispatcher is dead.");
			}
		}*/
	}
	
	public void dispose()
	{
		ifk.dispose();
		rfid.dispose();
		rfid = null;
		ifk = null;
	}
	
	public boolean getDigitalOutput(int i)
	{
    	synchronized(ifk.getDigitalOutputs())
    	{
			if(i<IFK.ifk_digital_Count)
			{
				return ifk.getDigitalOutputs()[i];
			}
			else
			{
				return false;
			}
    	}
	}

	public void setDigitalOutput(int i, boolean value)
	{
		synchronized(ifk.getDigitalOutputs())
		{
			ifk.setDigitalOutput(i, value);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof RFIDTagGainListener)
		{
			try
			{
				boolean goingto = false;
				TagGainEvent oe = (TagGainEvent)arg1;
				String id_person = oe.getValue();
				RFIDPhidget tempRfidph = (RFIDPhidget)(oe.getSource());
				try {
					tempRfidph.setLEDOn(true);
				} catch (PhidgetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				synchronized(rfid_goingto_tags)
				{
					if(!rfid_goingto_tags.contains(id_person))
					{
						rfid_goingto_tags.add(id_person);
						goingto=true;
					}
				}
				
				if(!goingto)
				{
					throw new RFIDUserAlreadyFoundException(id_person);
				}
			}
			catch(RFIDUserAlreadyFoundException e)
			{
				System.out.println("User already found : " + e.getMessage());
			}
		}
		else if(arg0 instanceof RFIDTagLossListener)
		{
			try
			{
				boolean present = false;
				TagLossEvent oe = (TagLossEvent)arg1;
				String id_person = oe.getValue();
				RFIDPhidget tempRfidph = (RFIDPhidget)(oe.getSource());
				try {
					tempRfidph.setLEDOn(false);
				} catch (PhidgetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized(rfid_goingto_tags)
				{
					if(rfid_goingto_tags.contains(id_person))
					{
						rfid_goingto_tags.remove(id_person);
						present = true;
					}
				}
				
				if(present)
				{
					synchronized(rfid_present_tags)
					{
						if(rfid_present_tags.contains(id_person))
						{
							rfid_present_tags.remove(id_person);
						}
						else
						{
							rfid_present_tags.add(id_person);
						}
					}
				}
				else
				{
					throw new RFIDUserNotFoundException(id_person);
				}
			}
			catch(RFIDUserNotFoundException e)
			{
				System.out.println("User not found : " + e.getMessage());
			}
		}
	}
}

//RFID : 