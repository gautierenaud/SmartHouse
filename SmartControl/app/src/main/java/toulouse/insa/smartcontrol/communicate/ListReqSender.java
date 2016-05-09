package toulouse.insa.smartcontrol.communicate;

import android.os.Debug;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListReqSender {
	
	private Socket clientSocket;
	private BufferedOutputStream sWriter;
	
	public ListReqSender(){
		InetAddress address;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			address = InetAddress.getByName("192.168.150.4");
			this.clientSocket = new Socket(address, 2042);
			this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendReq(ReqType req){
		System.out.println("sending message...");
		try{
			ObjectMapper mapper = new ObjectMapper();
			String test = mapper.writeValueAsString(req);
			sWriter.write(test.getBytes(), 0, test.getBytes().length);
			sWriter.flush();
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			closeSocket();
		}
	}
	
	public void closeSocket(){
		try {
			Log.e("SOCKET","alalla");
			if (this.sWriter != null) {
				this.sWriter.close();
				this.clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
