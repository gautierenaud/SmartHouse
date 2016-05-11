package frameself.executer;

import frameself.format.Action;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

public class ActionDispatcher
{
	static String serverAddress;
	static int serverPort;

	public ActionDispatcher()
	{
		serverAddress = frameself.main.Admin.prop.getProperty("specific_dispatcher_address");
		serverPort = Integer.parseInt(frameself.main.Admin.prop.getProperty("specific_dispatcher_port"));
		System.out.println("FRAMESELF: will send actions to specific dispatcher on " + serverAddress + ":" + serverPort);
	}

	public void dispatch(ArrayList<Action> actions) {
		for (Action action : actions) {
			send(action);
		}
	}

	public static void send(Action action)
	{
		try {
			InetAddress address = InetAddress.getByName(serverAddress);
			byte[] eventBytes = serialize(action);
			DatagramPacket packet = new DatagramPacket(eventBytes, eventBytes.length, address, serverPort);

			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			System.out.println("FRAMESELF: Sent action " + action.getCategory() + " to specific dispatcher");

			socket.close();
			Thread.sleep(200L);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in sending the Data to UDP Server");
		}
	}

	public static byte[] serialize(Object obj) throws java.io.IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ActionDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */