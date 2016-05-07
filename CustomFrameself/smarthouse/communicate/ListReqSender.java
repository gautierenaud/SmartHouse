package smarthouse.communicate;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListReqSender extends Thread{
	
	private Socket clientSocket;
	private BufferedOutputStream sWriter;
	
	public ListReqSender(){
		InetAddress address = InetAddress.getLoopbackAddress();
		try {
			this.clientSocket = new Socket(address, 2042);
			System.out.println(clientSocket);
			this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
			
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			ListRequest testRequest = new ListRequest(ReqType.ACTION);
			ObjectMapper mapper = new ObjectMapper();
			String test = mapper.writeValueAsString(testRequest);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
