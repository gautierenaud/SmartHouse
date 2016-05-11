package toulouse.insa.smartcontrol.communicate;

import android.util.Log;

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
	}

	public void initialize(){
		try {
			listenSocket = new ServerSocket(serverPort);
			System.out.println("ListAckReceiver starts listening to port " + serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		initialize();
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
				Log.d("ListAckReceiver", result);
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<Object> tmpArray = mapper.readValue(result, ArrayList.class);
				ArrayList<CustomRule> ruleList = new ArrayList<>();
				for (Object o : tmpArray){
					System.out.println(o.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
