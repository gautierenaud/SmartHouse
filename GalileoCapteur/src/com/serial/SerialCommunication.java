package com.serial;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.NoSuchPortException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialCommunication 
{
	private int baudSpeed;
	private String portName;
	private InputStream in;
	private OutputStream out;
	private CommPort commPort;
	
	public SerialCommunication(String portName, int baudSpeed)
	{
		this.baudSpeed = baudSpeed;
		this.portName = portName;
		try
		{
			this.connect();
		}
		catch(Exception e)
		{
			System.out.println("Error during serial connect.");
		}
	}
	
	void connect () throws Exception
    {
		try
		{
			
			/*Enumeration ports = CommPortIdentifier.getPortIdentifiers();
			while(ports.hasMoreElements())
			{
				CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
				System.out.println("coucouuuuuuuuuu " + port.getName());
			}*/
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			if ( portIdentifier.isCurrentlyOwned() )
	        {
	            System.out.println("Error: Port is currently in use");
	        }
	        else
	        {
	        	
	            commPort = portIdentifier.open(this.getClass().getName(),2000);
	            if ( commPort instanceof SerialPort )
	            {
	                SerialPort serialPort = (SerialPort) commPort;
	                serialPort.setSerialPortParams(baudSpeed,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	                in = serialPort.getInputStream();
	                out = serialPort.getOutputStream();
	                System.out.println("Serial communication successfully opened.");
	            }
	            else
	            {
	                System.out.println("Error: Only serial ports are handled.");
	            }
	        }     
		}
		catch(NoSuchPortException ex)
		{
			System.out.println("NoSuchPortException : first execute command 'sudo ln -s /dev/ttyACM[X] /dev/ttyS8[X]'");
		}
    }
	
	public void close()
	{
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commPort.close();
	}
	
	public void write(int[][] tab)
	{
		try
        {          
        	String byteStr = "";
        	for(int i=0;i<tab.length;i++)
        	{
        		for(int j=0;j<tab[i].length;j++)
        		{
        			byteStr += tab[i][j];
        		}
        	}
        	byte[] bytes = byteStr.getBytes();
			this.out.write(bytes);
			System.out.println(tab.length + " serial bytes written.");
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	public void write(byte[] bytes)
	{
		try
        {          
			this.out.write(bytes);
			System.out.println(bytes.length + " serial bytes written.");
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
    }
}
