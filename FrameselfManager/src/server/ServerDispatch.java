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
	
	private byte[] serialize(Object obj) throws java.io.IOException 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}
		
	private void send(String remoteIP, Action a)
	{
		try 
		{
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByAddress(remoteIP.getBytes());
			byte[] sendData = serialize(a);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8000);
			clientSocket.send(sendPacket);
			clientSocket.close();
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
				System.out.println("Dispatching " + actionName + "...");
				if(actionName.equals("SwitchLampOff") || actionName.equals("SwitchLampOn"))
				{
					dispatcher.send(action);
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