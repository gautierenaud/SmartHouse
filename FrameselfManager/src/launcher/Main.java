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
	 * gui (enable frameself's gui or not)
	 */
	public static void main(String[] args) 
	{
		Launcher launcher;
		if (args.length == 5)
			launcher = new Launcher(args[4]);
		else
			launcher = new Launcher();
		
		ServerAndroid serverAndroid = new ServerAndroid(launcher, Integer.parseInt(args[0]));
		serverAndroid.start();
		
		ServerDispatch serverDispatch = new ServerDispatch(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		serverDispatch.start();
		
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
			serverAndroid.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Communication closed.");
	}
}
