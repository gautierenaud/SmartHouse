package com.main;

import java.io.IOException;

import com.frameself.DispatcherFrameself;


public class Main 
{

	private final static int sleep_time = 1000; //ms
	private final static int threadFrameselfTimeout = 5*sleep_time;
	
	/*param
	 * ipResult (frameself)
	 * portSendResult (frameself)
	 * portListenAction (manager)
	 * portSerial
	 * baudSpeed
	 */
	public static void main(String[] args) 
	{
		if(args.length!=5)
		{
			System.out.println("Error args");
			System.exit(-1);
		}
		System.out.println("Serial port: " + args[3]);
		System.out.println("Press enter to end the program...");
		DispatcherFrameself	dispatcherFrameself = new DispatcherFrameself(args[0], Integer.parseInt(args[1]), 
																			Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));

		Thread thread_disp = new Thread(dispatcherFrameself);
		thread_disp.start();
		
		try 
		{
			System.in.read();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Closing communication...");
		dispatcherFrameself.stopRunning();
		try {
			thread_disp.join(threadFrameselfTimeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispatcherFrameself.dispose();
		System.gc();
		System.out.println("Communication closed.");
	}
}
