package smarthouse.communicate;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ListCommunicator implements Runnable{

	private int serverPort;
	private DatagramSocket receiver;
	private byte[] buf = new byte[1500];
	
	public ListCommunicator(int port) {
		this.serverPort = port;
		try {
			this.receiver = new DatagramSocket(this.serverPort);
		}catch (SocketException e){
			System.err.println(e);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
