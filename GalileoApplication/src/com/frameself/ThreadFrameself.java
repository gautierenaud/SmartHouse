package com.frameself;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;


import capteurs.ifk.IFK;
import capteurs.rfid.RFID;
import capteurs.rfid.RFIDTagGainListener;
import capteurs.rfid.RFIDTagLossListener;

import com.cexceptions.RFIDUserAlreadyFoundException;
import com.cexceptions.RFIDUserNotFoundException;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagLossEvent;

import java.util.*;

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
	private static final int houseTimeout = 20000;
	private Thread thread_dispatcher;
	private static final int timeoutThreadDispatcher = 5000;
	private String ipAddressCollector;
	private String ipAddressDispatcher;
	private int portCollector;
	private int portSendDispatcher;
	private int portListenDispatcher;
	
    public ThreadFrameself(String ipAddressCollector, int portCollector, String ipAddressDispatcher, int portSendDispatcher, int portListenDispatcher, String serialPortName, int baudSpeed, int sleep_time_thread)
    {
		System.out.println(Phidget.getLibraryVersion());
		rfid_present_tags = new ArrayList<String>();
		rfid_goingto_tags = new ArrayList<String>();
		this.ipAddressCollector = ipAddressCollector;
		this.portCollector = portCollector;
		this.ipAddressDispatcher = ipAddressDispatcher;
		this.portSendDispatcher = portSendDispatcher;
		this.portListenDispatcher = portListenDispatcher;
		ifk = new IFK();
		rfid = new RFID(this);
    	running_thread = true;
    	this.sleep_time = sleep_time_thread;
    	collframeself = new CollectorFrameself(this.ipAddressCollector, this.portCollector);
    	disframeself = new DispatcherFrameself(this, this.ipAddressDispatcher, this.portSendDispatcher, this.portListenDispatcher, serialPortName, baudSpeed);
    	thread_dispatcher = new Thread(disframeself);
    	thread_dispatcher.start();
    }
    
	public void run()
    {
		System.out.println("Initializing Frameself thread...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	ArrayList<String> rfid_saved_tags = new ArrayList<String>();
    	IFK.setMaxPressure(0);
		IFK.setMaxInfrared(0);
		System.out.println("Frameself Thread initialized.");
    	while(running_thread)
    	{
    		System.out.println("Acquiring data...");
    		int priority=0;
    		synchronized(rfid_present_tags)
    		{
    			ArrayList<String> rfid_tosend_tags = new ArrayList<String>();
    			ArrayList<String> rfid_temp_tags = new ArrayList<String>();
    			rfid_temp_tags.addAll(rfid_present_tags);
    			rfid_temp_tags.removeAll(rfid_saved_tags);
    			rfid_tosend_tags.addAll(rfid_temp_tags);
    			rfid_temp_tags.clear();
    			rfid_temp_tags.addAll(rfid_saved_tags);
    			rfid_temp_tags.removeAll(rfid_present_tags);
    			rfid_tosend_tags.addAll(rfid_temp_tags);
    			for(String tag : rfid_tosend_tags)
    			{
    				collframeself.sendEvent("RFID", tag, 0, houseTimeout);
    			}
    			if(!rfid_tosend_tags.isEmpty())
    			{
    				priority = 1;
    			}
    			rfid_saved_tags.clear();
        		rfid_saved_tags.addAll(rfid_present_tags);
    		}
    		if(IFK.getMaxInfrared() > 100)
    		{
    			collframeself.sendEvent("IR", String.valueOf(IFK.getMaxInfrared()), priority, houseTimeout);
    		}
    		
    		if(IFK.getMaxPressure() > 100)
    		{
    			collframeself.sendEvent("Pressure", String.valueOf(IFK.getMaxPressure()), priority, houseTimeout);
    		}
    		IFK.setMaxPressure(0);
    		IFK.setMaxInfrared(0);
	    	try {
				Thread.sleep(sleep_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("Frameself Thread finished.");
    }
	
	public void stopRunning()
	{
		running_thread = false;
	}
	
	public void dispose()
	{
		disframeself.dispose();
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