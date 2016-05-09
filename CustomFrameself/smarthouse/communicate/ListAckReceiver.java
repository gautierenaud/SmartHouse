package smarthouse.communicate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListAckReceiver extends Thread{

	private int serverPort = 2043;
	private ServerSocket listenSocket;
	
	public ListAckReceiver(){
		try {
			listenSocket = new ServerSocket(serverPort);
			System.out.println("ListAckReceiver starts listening to port " + serverPort);
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (true){
			Socket connectedSocket;
			try {
				connectedSocket = listenSocket.accept();
				byte[] tmpBuf = new byte[8000];
				BufferedInputStream inFromClient = new BufferedInputStream(connectedSocket.getInputStream());
				String result = "";
				int r = 0;
				r = inFromClient.read(tmpBuf);
				while ( r != -1){
					result += new String(tmpBuf, 0, r);
					r = inFromClient.read(tmpBuf);
				}
				System.out.println(result);
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<Object> ans = mapper.readValue(result, ArrayList.class);
				System.out.println("ack: " + ans);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
