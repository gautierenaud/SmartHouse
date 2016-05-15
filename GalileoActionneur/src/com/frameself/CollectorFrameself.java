package com.frameself;
import frameself.Collector;
import frameself.format.Event;
import java.util.Date;

public class CollectorFrameself 
{
	
	// Create a collector that publishes events to FRAMSELF on ipAdress:port
	private String ipAddress;
	private int port;
	private static Collector collector;
	private final String location = "Home";
	
	public CollectorFrameself(String ipAddress, int port)
	{
		this.ipAddress = ipAddress;
		this.port = port;
		collector = new Collector(this.ipAddress,this.port);
	}

	public void sendEvent(String category, int timeout)
	{
		Event event = new Event();
		event.setLocation(location);
		event.setTimestamp(new Date());
		event.setCategory(category);
		event.setExpiry(new Date(System.currentTimeMillis()+timeout));
		collector.send(event);
	}
	
	public void sendEvent(String category, String value, int timeout)
	{
		Event event = new Event();
		event.setLocation(location);
		event.setTimestamp(new Date());
		event.setCategory(category);
		event.setValue(value);
		event.setExpiry(new Date(System.currentTimeMillis()+timeout));
		collector.send(event);
	}
}
