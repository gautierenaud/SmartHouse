package launcher;

import java.io.IOException;

public class Launcher implements Runnable {

	private Process proc;
	private String command = "java -jar frameself.jar";
	private boolean gui;
	
	public Launcher(boolean gui){
		this.gui = gui;
		this.command += this.gui ? " gui" : "";
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
			int exitValue = proc.waitFor();
			System.out.println("Application exited with code :" + exitValue);
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
