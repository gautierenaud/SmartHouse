package main;

import java.io.IOException;

import frameselfcom.ThreadFrameself;

public class Main 
{

	private final static int sleep_time = 1000; //ms
	private final static int threadFrameselfTimeout = 5*sleep_time;
	/*param :
	 * ipEvent (frameself)
	 * portEvent (frameself)
	 */
	public static void main(String[] args) 
	{
		if(args.length!=2)
		{
			System.out.println("Error args");
			System.exit(-1);
		}
		if(!System.getProperty("user.name").equals("root"))
		{
			System.out.println("You must run this program with root rights or you won't be able to use phidget components.");
			System.exit(-1);
		}
			
		ThreadFrameself thread_frameself = new ThreadFrameself(args[0], Integer.parseInt(args[1]), sleep_time);
		Thread thread_comm = new Thread(thread_frameself);
		thread_comm.start();
			
		try 
		{
			System.out.println("Press enter to stop the program.");
			System.in.read();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Closing communication...");
		thread_frameself.stopRunning();
		try {
			thread_comm.join(threadFrameselfTimeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread_frameself.dispose();
		System.gc();
		System.out.println("Communication closed.");
	}
}
