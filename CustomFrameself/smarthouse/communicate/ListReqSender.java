package smarthouse.communicate;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import toulouse.insa.smartcontrol.communicate.ReqType;

public class ListReqSender {
	
	private Socket clientSocket;
	private BufferedOutputStream sWriter;
	
	public ListReqSender(){
		InetAddress address = InetAddress.getLoopbackAddress();
		try {
			this.clientSocket = new Socket(address, 2042);
			this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendReq(ReqType req){
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
			this.sWriter.close();
			this.clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
