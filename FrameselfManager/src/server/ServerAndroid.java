package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import launcher.Launcher;
import order.Order;
import order.RuleFrameself;
import order.Order.OrderType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerAndroid implements Runnable {

	private DatagramSocket socket;
	private int port;
	private boolean thread_running;
	private Launcher launcher;
	private boolean ctrlcharError;
	private Thread thread_frameselfApplication;
	
	public ServerAndroid(Launcher launcher, int port)
	{
		this.launcher = launcher;
		this.port = port;
		thread_running = true;
		ctrlcharError = false;
	}
	
	public void close()
	{
		socket.close();
		
	}
	
	public void stopRunning()
	{
		thread_running = false;
		if(thread_frameselfApplication.isAlive())
		{
			launcher.stop();
			try {
				thread_frameselfApplication.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Order receive() throws SocketException
	{
		byte[] msgBuffer = new byte['Ѐ'];
	    DatagramPacket packet = new DatagramPacket(msgBuffer, msgBuffer.length);
	    try {
			this.socket.receive(packet);
		} catch (IOException e) {
			if(!e.getMessage().equals("Socket closed"))
			{
				e.printStackTrace();
			}
		}
	    
	    ObjectMapper mapper = new ObjectMapper();
		try {
			Order order = mapper.readValue(msgBuffer, Order.class);
			return order;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(!e.getMessage().contains(("CTRL-CHAR")))
			{
				e.printStackTrace();
			}
			else
			{
				ctrlcharError = true;
			}
		}
	    return null;
	}

	@Override
	public void run() 
	{
		thread_frameselfApplication = new Thread(launcher);
		try 
		{
			 this.socket = new DatagramSocket(port);
			 System.out.println("ServerAndroid: listening on port " + port);
			 thread_frameselfApplication.start();
		} 
		catch (SocketException e) 
		{
			e.printStackTrace();
			thread_running = false;
		}
		while(thread_running)
		{
			try {
				Order order = this.receive();
				
				if(order.getOrderType() == OrderType.START)
				{
					System.out.println("Order received : START.");
					if(!thread_frameselfApplication.isAlive())
					{
						thread_frameselfApplication = new Thread(launcher);
						thread_frameselfApplication.start();
					}
					else
					{
						System.out.println("Application already started.");
					}
				}
				else if(order.getOrderType() == OrderType.STOP)
				{
					System.out.println("Order received : STOP.");
					if(thread_frameselfApplication.isAlive())
					{
						launcher.stop();
						try {
							thread_frameselfApplication.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						System.out.println("Application already stopped.");
					}
				}
				else if(order.getOrderType() == OrderType.ADDRULE)
				{
					System.out.println("Order received : ADDRULE.");
					RuleFrameself rule = order.getRule();
					if(rule == null)
					{
						System.out.println("No rule found.");
					}
					else
					{
						boolean added = true;
						if(thread_frameselfApplication.isAlive())
						{
							System.out.println("Stopping frameself...");
							launcher.stop();
							try {
								thread_frameselfApplication.join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						System.out.println("Creating backup...");
						try
						{
							Files.copy(Paths.get("rules/planner/planinference.drl"), Paths.get("rules/planner/.planinference.drl.backup"));
						} catch (IOException e1) 
						{
							System.out.println("Couldn't create backup. Rules won't be added.");
						}
						System.out.println("Adding " + rule.getRuleName() + "...");
						try 
						{
						    Files.write(Paths.get("rules/planner/planinference.drl"), rule.generateRule(), StandardOpenOption.APPEND);
						}
						catch (IOException e) {
							added = false;
							try {
								Files.deleteIfExists(Paths.get("rules/planner/planinference.drl"));
								Files.copy(Paths.get("rules/planner/.planinference.drl.backup"), Paths.get("rules/planner/planinference.drl"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						    System.out.println("Couldn't add all rules, file restored.");
						}
						if(added)
						{
							System.out.println("Rules correctly added.");
							try {
								Files.deleteIfExists(Paths.get("rules/planner/.planinference.drl.backup"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Backup deleted.");
						}
						System.out.println("Restarting frameself...");
						thread_frameselfApplication = new Thread(launcher);
						thread_frameselfApplication.start();
					}
					//launcher.addRule();
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				if(!thread_running)
				{
					System.out.println("Socket closed.");
				}
				else
				{
					e.printStackTrace();
				}
			}
			catch(NullPointerException e)
			{
				if(!ctrlcharError)
				{
					e.printStackTrace();
				}
				else
				{
					ctrlcharError = false;
				}
			}
		}	
	}
}
