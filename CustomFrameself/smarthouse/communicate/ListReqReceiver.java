package smarthouse.communicate;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import com.fasterxml.jackson.databind.ObjectMapper;

import jxl.biff.ByteArray;

public class ListReqReceiver extends Thread{
	
	private int bufferSize = 8192;
	private int serverPort = 2042;
	private ServerSocket listenSocket;
	
	public ListReqReceiver(){
		try {
			listenSocket = new ServerSocket(this.serverPort);
			System.out.println("start listending to port " + serverPort);
			this.start();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (true){
			try {
				Socket connectedSocket = listenSocket.accept();
				byte[] tmpBuf = new byte[bufferSize];
				BufferedInputStream inFromClient = new BufferedInputStream(connectedSocket.getInputStream());
				String result = "";
				int r = 0;
				r = inFromClient.read(tmpBuf);
				while ( r != -1){
					result += new String(tmpBuf, 0, r);
					r = inFromClient.read(tmpBuf);
				}
				System.out.println("toto: " + result);
				ObjectMapper mapper = new ObjectMapper();
				ListRequest req = mapper.readValue(result, ListRequest.class);
				System.out.println("toto: " + req);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
