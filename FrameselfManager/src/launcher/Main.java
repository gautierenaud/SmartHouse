package launcher;
import java.io.IOException;

import server.ServerAndroid;
import server.ServerDispatch;

public class Main {

	/*param :
	 * portAndroid
	 * ipSendAction (actionner)
	 * portSendAction (actionner)
	 * portGetAction (frameself)
	 */
	public static void main(String[] args) 
	{
		
		Launcher launcher = new Launcher(args.length == 5);
		ServerAndroid serverAndroid = new ServerAndroid(launcher, Integer.parseInt(args[0]));
		ServerDispatch serverDispatch = new ServerDispatch(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		Thread thread_serverAndroid = new Thread(serverAndroid);
		Thread thread_serverDispatch = new Thread(serverDispatch);
		thread_serverAndroid.start();	
		thread_serverDispatch.start();
		
		try {
			System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Closing communication...");
		serverAndroid.stopRunning();
		serverAndroid.close();	
		serverDispatch.stopRunning();
		serverDispatch.close();
		
		try {
			thread_serverAndroid.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Communication closed.");
	}
}
