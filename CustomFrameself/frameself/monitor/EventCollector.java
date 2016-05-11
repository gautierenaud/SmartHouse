package frameself.monitor;

import frameself.format.Event;
import frameself.main.Admin;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class EventCollector implements Runnable
{
	private static ArrayList<Event> events;
	private Event event;
	int serverPort;

	public EventCollector()
	{
		events = new ArrayList();
		this.serverPort = Integer.parseInt(Admin.prop.getProperty("frameself_events_listener_port"));
	}

	public static ArrayList<Event> getEvents()
	{
		return events;
	}

	public Object[] createEventObject(Event e) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), e.getTimestamp(), e.getExpiry() };
	}



	public void run()
	{
		System.out.println("FRAMESELF: listening for events on port " + this.serverPort);
		try {
			DatagramSocket socket = new DatagramSocket(this.serverPort);
			byte[] msgBuffer = new byte['Ѐ'];
			DatagramPacket packet = new DatagramPacket(msgBuffer, msgBuffer.length);
			packet.setLength(msgBuffer.length);
			for (;;) {
				socket.receive(packet);
				this.event = ((Event)deserialize(msgBuffer));
				System.out.println("Monitor: Event received = " + this.event.getCategory());
				EventCollector.events.add(EventCollector.this.event);
				if (Admin.useGUI){
					javax.swing.SwingUtilities.invokeLater(new Runnable()
					{
						public void run() {
							System.out.println(EventCollector.this.event.getId() + ", " + EventCollector.this.event.getValue() + ", " + EventCollector.this.event.getTimestamp() + ", " + EventCollector.this.event.getExpiry());
							DefaultTableModel model = (DefaultTableModel)frameself.gui.GuiAdmin.getReceivedEvents().getModel();
							model.addRow(EventCollector.this.createEventObject(EventCollector.this.event));
						}
	
	
					});
				}else{
					System.out.println(EventCollector.this.event.getId() + ", " + EventCollector.this.event.getValue() + ", " + EventCollector.this.event.getTimestamp() + ", " + EventCollector.this.event.getExpiry());
					EventCollector.events.add(EventCollector.this.event);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in getting the Data from UDP Client");
		}
	}


	public static Object deserialize(byte[] data)
			throws java.io.IOException, ClassNotFoundException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	public static void setEvents(ArrayList<Event> events)
	{
		events = events;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */