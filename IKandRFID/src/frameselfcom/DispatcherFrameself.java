package frameselfcom;
import frameself.Dispatcher;
import frameself.format.Action;

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
	
	public DispatcherFrameself(ThreadFrameself thread_frameself, String ipAddress, int sendPort, int listeningPort)
	{
		this.ipAddress = ipAddress;
		this.sendPort = sendPort;
		this.listeningPort = listeningPort;
		thread_running = true;
		dispatcher = new Dispatcher(this.ipAddress,this.sendPort,this.listeningPort);
		this.thread_frameself = thread_frameself;
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	@Override
	public void run() 
	{
		boolean light = false;
		while(this.thread_running)
		{
			Action action = dispatcher.receive();
			String actionName = action.getName();
			if(actionName.equals("SwitchLampOff"))
			{
				if(thread_frameself.getDigitalOutput(0))
				{
					thread_frameself.setDigitalOutput(0, false);
					action.setResult("true");
					action.setError("No error");
				}
				else
				{
					action.setResult("false");
					action.setError("Lamp already off");
				}
				System.out.println("Light : " + light);
			}
			else if(actionName.equals("SwitchLampOn"))
			{
				if(!thread_frameself.getDigitalOutput(0))
				{
					thread_frameself.setDigitalOutput(0, true);
		   			action.setResult("true");
					action.setError("No error");
				}
				else
				{
					action.setResult("false");
					action.setError("Lamp already on");
				}
				System.out.println("Light : " + light);
			}
			else if(actionName.equals("CloseProgram"))
			{
				action.setResult("true");
				action.setError("No error");
			}
			System.out.println(action.getName()+" excuted");
			dispatcher.send(action);
		}
	}
}
