package toulouse.insa.smartcontrol.communicate;

import android.os.Debug;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;

import frameself.format.Event;
import toulouse.insa.smartcontrol.params.Parameters;

public class ListReqSender extends Thread{
	
	private Socket clientSocket;
	private BufferedOutputStream sWriter;

	private static ArrayBlockingQueue<ReqType> reqQueue = new ArrayBlockingQueue<>(10);
	
	public ListReqSender(){}

	public void initialize(){
		try {
			// connect to the frameself custom socket
			Log.d("ListReqSender", "Connecting to " + Parameters.getFrameselfAddress());
			this.clientSocket = new Socket(Parameters.getFrameselfAddress(), 2042);
			this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addReq(ReqType req){
		Log.d("ListReqSender", "Adding " + req + " to the queue");
		reqQueue.add(req);
	}

	public void run(){
		while(true) {
			try {
				ReqType req = reqQueue.take();
				initialize();
				Log.d("ListReqSender", "Sending ReqType: " + req + " to " + clientSocket);
				ObjectMapper mapper = new ObjectMapper();
				String sendString = mapper.writeValueAsString(req);
				sWriter.write(sendString.getBytes(), 0, sendString.getBytes().length);
				sWriter.flush();
				closeSocket();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void closeSocket(){
		try {
			if (this.sWriter != null) {
				this.sWriter.close();
				this.clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// will send one req for each ReqType (RFC, SYMPTOM, ACTION)
	public void sendAllReq(){
		for  (ReqType req : ReqType.values())
			this.addReq(req);
	}
}
