package launcher;
import java.io.IOException;

import server.*;

public class Main {

	/*
	 *frameself : no ip needed (127.0.0.1)
	 *portGetAction (frameself) : 6000
	 *portSendResult (frameself) : 7000
	 *portGetResult (actionner) : 10000
	 *portSendAction (actionner) : 9000
	 
	 *android : no ip needed
	 *portAndroid : 8000 (socketUDP)
	 
	 *actionner : 127.0.0.1
	 *portGetAction (actionner) : 9000
	 *portSendResult (actionner) : 10000
	 */
	
	public static void main(String[] args) 
	{
		
		Launcher launcher = new Launcher();
		
		ServerFrameself serverFrameself = new ServerFrameself("127.0.0.1", Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		ServerAndroid serverAndroid = new ServerAndroid(launcher, Integer.parseInt(args[2]));
		ServerActionner serverActionner = new ServerActionner(args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
		Thread thread_serverFrameself = new Thread(serverFrameself);
		Thread thread_serverAndroid = new Thread(serverAndroid);
		Thread thread_serverActionner = new Thread(serverActionner);
		
		thread_serverFrameself.start();
		thread_serverAndroid.start();	
		thread_serverActionner.start();
		
		try {
			System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Closing communication...");
		serverActionner.stopRunning();
		serverActionner.close();
		serverAndroid.stopRunning();
		serverAndroid.close();	
		serverFrameself.stopRunning();
		serverFrameself.close();
	
		try {
			thread_serverActionner.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			thread_serverAndroid.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			thread_serverFrameself.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Communication closed.");
	}
}
