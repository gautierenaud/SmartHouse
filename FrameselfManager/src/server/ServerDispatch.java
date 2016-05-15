package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import frameself.Dispatcher;
import frameself.format.Action;
public class ServerDispatch implements Runnable {

	private DatagramSocket socket;
	private boolean thread_running;
	private Dispatcher dispatcher;
	private String ipAddress;
	private int sendPort;
	private int listeningPort;
	
	public ServerDispatch(String ipAddress, int portSend, int portListening)
	{
		this.ipAddress = ipAddress;
		this.sendPort = portSend;
		this.listeningPort = portListening;
		thread_running = true;
		dispatcher = new Dispatcher(this.ipAddress,this.sendPort,this.listeningPort);
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	public void close()
	{
		dispatcher.close();
	}
	
	@Override
	public void run()
	{
		while(thread_running)
		{
			try
			{
				Action action = dispatcher.receive();
				String actionName = action.getName();
				String actionCategory = action.getCategory();
				System.out.println("Dispatching " + actionName + "...");
				if(actionCategory.equals("LED"))
				{
					if(actionName.equals("setText"))
					{
						dispatcher.send(action);
					}
				}
				else if(actionCategory.equals("Phillips"))
				{
					if(actionName.equals("changeColor"))
					{
						dispatcher.send(action);
					}
				}
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
			}
		}
	}
}
