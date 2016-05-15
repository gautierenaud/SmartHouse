package server;


import frameself.Dispatcher;
import frameself.format.Action;
public abstract class ServerDispatch implements Runnable {

	private boolean thread_running;
	private Dispatcher dispatcher;
	private String ipSource;
	private int portFrom;
	private int portTo;
	
	public ServerDispatch(String ipSource, int portFrom, int portTo)
	{
		this.ipSource = ipSource;
		this.portFrom = portFrom;
		this.portTo = portTo;
		this.thread_running = true;
		this.dispatcher = new Dispatcher(this.ipSource ,this.portTo, this.portFrom);
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	
	public void close()
	{
		dispatcher.close();
	}
	
	public void sendAction(Action action)
	{
		try
		{
			dispatcher.send(action);
			System.out.println("Action dispatched.");
		}
		catch(NullPointerException e)
		{
			
		}
	}
	
	public abstract void treatAction(Action action);
	
	@Override
	public final void run()
	{
		while(thread_running)
		{
			try
			{
				Action action = dispatcher.receive();
				System.out.println("Dispatching " + action.getName() + "...");
				treatAction(action);
			}
			catch(NullPointerException e)
			{
				if(thread_running)
				{
					e.printStackTrace();
				}
				else
				{
					System.out.println("Dispatch server stopped.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
