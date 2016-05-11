package frameself.executer;

import frameself.format.Action;
import frameself.format.Attribute;
import frameself.format.Event;
import frameself.main.Admin;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ActionResultReceiver implements Runnable
{
	private static ArrayList<Event> actions;
	int serverPort;
	private Action action;

	public ActionResultReceiver()
	{
		actions = new ArrayList();
		this.serverPort = Integer.parseInt(Admin.prop.getProperty("frameself_actionResults_listener_port"));
	}

	public Object[] createEventObject(Event e) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), 
				e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), 
				Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), 
				e.getTimestamp(), e.getExpiry() };
	}


	public void run()
	{
		System.out.println("FRAMESELF: listening for action results from specific dispatcher on port " + this.serverPort);
		try {
			DatagramSocket socket = new DatagramSocket(this.serverPort);
			byte[] msgBuffer = new byte['Ѐ'];
			DatagramPacket packet = new DatagramPacket(msgBuffer, 
					msgBuffer.length);
			packet.setLength(msgBuffer.length);
			for (;;) {
				socket.receive(packet);
				this.action = ((Action)deserialize(msgBuffer));
				System.out.println("Monitor: Action received = " + 
						this.action.getName() + " " + this.action.getResult());

				if (Admin.useGUI){
					javax.swing.SwingUtilities.invokeLater(new Runnable()
					{
						public void run() {
							System.out.println(ActionResultReceiver.this.action.getName() + " " + ActionResultReceiver.this.action.getResult());
							DefaultTableModel model = (DefaultTableModel) frameself.gui.GuiAdmin.getActionsResult().getModel();
							model.addRow(ActionResultReceiver.this.createActionResultObject(ActionResultReceiver.this.action));
	
							System.out.println(ActionResultReceiver.this.action.getName() + " " + ActionResultReceiver.this.action.getResult());
							DefaultTableModel model2 = (DefaultTableModel) frameself.gui.GuiAdmin.getActionsResultTable().getModel();
							model2.addRow(ActionResultReceiver.this.createActionResultObject(ActionResultReceiver.this.action));
						}
					});
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in getting the Data from UDP Client");
		}
	}


	public Object[] createActionResultObject(Action a)
	{
		String parameters = "";
		for (int i = 0; i < a.getAttributes().size(); i++) {
			parameters = 
					parameters + ((Attribute)a.getAttributes().get(i)).getName() + "=" + ((Attribute)a.getAttributes().get(i)).getValue();
			if (i > 0) {
				parameters = parameters + "&";
			}
		}
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), 
				a.getCategory(), a.getName(), parameters, Integer.valueOf(a.getPriority()), 
				a.getDescription(), a.getEffector().getName(), a.getResult(), 
				a.getError(), a.getTimestamp() };
	}

	public static Object deserialize(byte[] data) throws java.io.IOException, ClassNotFoundException
	{
		java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ActionResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */