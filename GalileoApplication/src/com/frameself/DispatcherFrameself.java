package com.frameself;
import java.util.ArrayList;

import com.serial.*;

import actionneurs.led.LEDManager;
import actionneurs.led.Tableau_led;
import actionneurs.phillips.PhillipsLamp;
import frameself.Dispatcher;
import frameself.format.Action;
import frameself.format.Attribute;

public class DispatcherFrameself implements Runnable 
{
	// Create a dispatcher that listens to actions from FRAMESELF on port listeningPort and
	// sends back results on ipAddress:sendPort
	private String ipAddress;
	private int listeningPort;
	private int sendPort;
	private Dispatcher dispatcher;
	private boolean thread_running;
	private ThreadFrameself thread_frameself;
	private String serialPortName;
	private SerialCommunication serialcom;
	private int baudSpeed;
	private PhillipsLamp lamp;
	private Tableau_led tabLED;
	private Thread thread_managerLED;
	private LEDManager ledManager;
	private Thread thread_lamp;
	
	
	public DispatcherFrameself(ThreadFrameself thread_frameself, String ipAddress, int sendPort, int listeningPort, String serialPortName, int baudSpeed)
	{
		this.serialPortName = serialPortName;
		this.ipAddress = ipAddress;
		this.thread_running = true;
		this.thread_frameself = thread_frameself;
		//this.tabLED = new Tableau_led();
		this.baudSpeed = baudSpeed;
		this.sendPort = sendPort;
		this.listeningPort = listeningPort;
		this.dispatcher = new Dispatcher(this.ipAddress,this.sendPort,this.listeningPort);
		System.out.println("IP: " + this.ipAddress);
		System.out.println("PortSend: " + this.sendPort);
		System.out.println("PortListen: " + this.listeningPort);
		//this.serialcom = new SerialCommunication(this.serialPortName, this.baudSpeed);
		this.lamp = new PhillipsLamp();
	}
	
	public void dispose()
	{
		try
		{
			//serialcom.close();
		}
		catch(NullPointerException e)
		{
			
		}
		
		try
		{
			dispatcher.close();
		}
		catch(NullPointerException e)
		{
			
		}
		System.out.println("Disposing...");
		lamp.close();
		if(thread_lamp!=null && thread_lamp.isAlive())
		{
			try
			{
				
				thread_lamp.join();
			}
			catch(InterruptedException e)
			{
				
			}
			catch(NullPointerException e)
			{
				
			}
		}
		//ledManager.stopRunning();
		/*if(thread_managerLED!=null && thread_lamp.isAlive())
		{
			try
			{
				thread_managerLED.join();
			}
			catch(InterruptedException e)
			{
				
			}
		}*/
		
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	public void run()
	{
		try
		{
			this.lamp.se_connecter();
			this.lamp.test_connexion();
			while(this.thread_running)
			{
				System.out.println("Receiving action...");
				Action action = dispatcher.receive();
				String actionName = action.getName();
				String actionCategory = action.getCategory();
				System.out.println(actionName + " received.");
				if(actionCategory.equals("LED"))
				{
					if(actionName.equals("setText"))
					{
						System.out.println("It is a setText.");
						String text = "";
						for(Attribute attribute : action.getAttributes())
						{
							if(attribute.getName().equals("text"))
							{
								text = attribute.getValue();
								System.out.println(text);
								break;
							}
						}
						/*if(thread_managerLED!=null && thread_managerLED.isAlive())
						{
							ledManager.stopRunning();
							try
							{
								thread_managerLED.join();
							}
							catch(InterruptedException e)
							{
								
							}
						}*/
						if(text.equals(""))
						{
							//tabLED.set_text(" ", 0, 0);
							//serialcom.write(tabLED.toByte());
							action.setResult("true");
							action.setError("No error");
						}
						else
						{
							System.out.println("Starting LEDManager...");
							//ledManager = new LEDManager(tabLED, text, this.serialcom);
							//thread_managerLED = new Thread(ledManager);
							//thread_managerLED.start();
							action.setResult("true");
							action.setError("No error");
						}
					}
				}
				else if(actionCategory.equals("Phillips"))
				{
					if(actionName.equals("changeColor"))
					{
						System.out.println("It is a changeColor.");
						if(thread_lamp!=null && thread_lamp.isAlive())
						{
							lamp.close();
							try
							{
								thread_lamp.join();
							}
							catch(InterruptedException e)
							{
								
							}
						}
						int red = -1;
						int green = -1;
						int blue = -1;
						int brightness = -1;
						for(Attribute attribute : action.getAttributes())
						{
							if(attribute.getName().equals("red"))
							{
								red = Integer.parseInt(attribute.getValue());
							}
							else if(attribute.getName().equals("green"))
							{
								green = Integer.parseInt(attribute.getValue());
							}
							if(attribute.getName().equals("blue"))
							{
								blue = Integer.parseInt(attribute.getValue());
							}
							else if(attribute.getName().equals("brightness"))
							{
								brightness = Integer.parseInt(attribute.getValue());
							}
							else if(red!=-1 && green!=-1 && blue!=-1 && brightness!=-1)
							{
								break;
							}
						}
						if(red==0 && green ==0 && blue == 0 && brightness == 0)
						{
							lamp.set_etat_lampe_1(false);
							action.setResult("true");
							action.setError("No error");
						}
						else if(red!=-1 && green!=-1 && blue!=-1 && brightness!=-1)
						{
							lamp.set_etat_lampe_1(true);
							System.out.println("Change color : r=" + red + "; g=" + green + "; b=" + blue + "; br=" + brightness);
							lamp.changer_couleur(red, green, blue, brightness, 255, 1);
							action.setResult("true");
							action.setError("No error");
						}
						else
						{
							System.out.println("Couldn't find all attributes to change color lamp");
							action.setResult("false");
							action.setError("Couldn't find all attributes to change color lamp");
						}
						
					}
					else if(actionName.equals("setAmbiance"))
					{
						String ambiance = "";
						for(Attribute attribute : action.getAttributes())
						{
							if(attribute.getName().equals("ambiance"))
							{
								ambiance = attribute.getValue();
								break;
							}
						}
						if(!ambiance.equals(""))
						{
							if(thread_lamp!=null && thread_lamp.isAlive())
							{
								lamp.close();
								try
								{
									thread_lamp.join();
								}
								catch(InterruptedException e)
								{
									
								}
							}
							lamp.set_etat_lampe_1(true);
							lamp.setColorType(ambiance);
							thread_lamp = new Thread(lamp);
							thread_lamp.start();
							action.setResult("true");
							action.setError("No error");
						}
						else
						{
							System.out.println("Couldn't find lamp ambiance.");
							action.setResult("false");
							action.setError("Couldn't find lamp ambiance.");
						}
					}
				}
				dispatcher.send(action);
			}
		}
		catch(Exception e)
		{
			System.out.println("Dispatcher thread has finished with error : " + e.getMessage());
			e.getStackTrace();
		}
		System.out.println("Dispatcher Thread finished.");
	}
}