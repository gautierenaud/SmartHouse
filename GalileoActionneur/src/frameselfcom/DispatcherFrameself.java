package frameselfcom;
import actionneurs.TableauLED;
import actionneurs.Tableau_led;
import frameself.Dispatcher;
import frameself.format.Action;
import serialcom.*;

public class DispatcherFrameself implements Runnable 
{
	// Create a dispatcher that listens to actions from FRAMESELF on port listeningPort and
	// sends back results on ipAddress:sendPort
	private String ipAddress;
	private int listeningPort;
	private int sendPort;
	private Dispatcher dispatcher;
	private boolean thread_running;
	private SerialCommunication serialcom;
	private String portName;
	private int baudSpeed;
	private String tabLedStr;
	private Tableau_led tabLED;
	public DispatcherFrameself(String ipAddress, int sendPort, int listeningPort, String portName, int baudSpeed)
	{
		tabLedStr = "null";
		tabLED = new Tableau_led();
		this.ipAddress = ipAddress;
		this.baudSpeed = baudSpeed;
		this.portName = portName;
		this.sendPort = sendPort;
		this.listeningPort = listeningPort;
		this.thread_running = true;
		this.dispatcher = new Dispatcher(this.ipAddress,this.sendPort,this.listeningPort);
		this.serialcom = new SerialCommunication(this.portName, this.baudSpeed);
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	public void dispose()
	{
		serialcom.close();
		dispatcher.close();
	}
	
	@Override
	public void run() 
	{
		while(this.thread_running)
		{
			try
			{
				Action action = dispatcher.receive();
				String actionName = action.getName();
				if(actionName.equals("SwitchLampOn")) //displaybonjour
				{
					if(!tabLedStr.equals("Bonjour"))
					{
						tabLED.set_text("bonjour", 10, 0);
						serialcom.write(tabLED.toByte());
						tabLedStr = "Bonjour";
						action.setResult("true");
						action.setError("No error");
					}
					else
					{
						action.setResult("false");
						action.setError(tabLedStr + " is already being displayed.");
					}
				}
				else if(actionName.equals("SwitchLampOff"))
				{
					if(!tabLedStr.equals(""))
					{
						int[][] buffer = TableauLED.nulle;
						serialcom.write(buffer);
						tabLedStr = "";
						action.setResult("true");
						action.setError("No error");
					}
					else
					{
						action.setResult("false");
						action.setError(tabLedStr + " is already being displayed.");
					}
				}
				System.out.println(action.getName()+" excuted");
				dispatcher.send(action);
			}
			catch(NullPointerException e)
			{
				System.out.println("Dispatcher thread has finished.");
				break;
			}
		}
	}
}
