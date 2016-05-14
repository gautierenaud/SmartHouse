package launcher;

import java.io.IOException;

public class Launcher implements Runnable {

	private Process proc;
	private String command = "java -jar SmartFrameself.jar";

	public Launcher (String gui) {
		command += " " + gui;
	}
	
	public Launcher () {
	}
	
	public void start()
	{
		try {
			System.out.println("Command: " + command);
			proc = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Application started.");
	}
	
	public void stop()
	{
		proc.destroy();
		System.out.println("Application stopped.");
	}
	
	public void waitForProcEnd()
	{
		try {
			System.out.println("Waiting for application to finish...");
			int returnVal = proc.waitFor();
			System.out.println("Application exited with code: " + returnVal);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		this.start();
		this.waitForProcEnd();
	}
}
