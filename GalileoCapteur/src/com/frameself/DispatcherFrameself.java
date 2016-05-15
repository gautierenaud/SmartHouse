package com.frameself;
import java.util.ArrayList;

import com.serial.*;

import actionneurs.led.TableauLED;
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
	private String portName;
	private int baudSpeed;
	private PhillipsLamp lamp;
	private Tableau_led tabLED;

	
	public DispatcherFrameself(ThreadFrameself thread_frameself, String ipAddress, int sendPort, int listeningPort, String serialPortName, int baudSpeed)
	{
		this.ipAddress = ipAddress;
		this.thread_running = true;
		this.thread_frameself = thread_frameself;
		this.tabLED = new Tableau_led();
		this.baudSpeed = baudSpeed;
		this.serialPortName = serialPortName;
		this.sendPort = sendPort;
		this.listeningPort = listeningPort;
		this.dispatcher = new Dispatcher(this.ipAddress,this.sendPort,this.listeningPort);
		this.serialcom = new SerialCommunication(this.portName, this.baudSpeed);
		this.lamp = new PhillipsLamp();
	}
	
	public void dispose()
	{
		try
		{
			serialcom.close();
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
		
		try
		{
			lamp.close();
		}
		catch(NullPointerException e)
		{
			
		}
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	public void run()
	{
		this.lamp.se_connecter();
		this.lamp.test_connexion();
		System.out.println("Thread started.");
		while(this.thread_running)
		{
			try
			{
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
						if(text.equals(""))
						{
							action.setResult("false");
							action.setError("Couldn't find attribute 'text'");
						}
						else
						{
							System.out.println("Writing...");
							tabLED.set_text(text, 10, 0);
							serialcom.write(tabLED.toByte());
							action.setResult("true");
							action.setError("No error");
						}
						dispatcher.send(action);
						
					}
				}
				else if(actionCategory.equals("Phillips"))
				{
					if(actionName.equals("changeColor"))
					{
						System.out.println("It is a changeColor.");
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
						if(red!=-1 && green!=-1 && blue!=-1 && brightness!=-1)
						{
							//allumer lampe
							System.out.println("Change color : r=" + red + "; g=" + green + "; b=" + blue + "; br=" + brightness);
							//lamp.changer_couleur(200, 20, 20, 127, 250, 1);
							//lamp.changer_couleur(red, green, blue, brightness, 250, 0);
							lamp.set_ambiance_nature(100, 600);
							action.setResult("true");
							action.setError("No error");
						}
						else
						{
							action.setResult("false");
							action.setError("Couldn't find all attributes to change color lamp");
						}
						dispatcher.send(action);
						
					}
				}
			}
			catch(NullPointerException e)
			{
				System.out.println("Dispatcher thread has finished.");
				break;
			}
		}
		System.out.println("Thread finished.");
	}
}