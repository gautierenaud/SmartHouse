package toulouse.insa.smartcontrol.communicate;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import toulouse.insa.smartcontrol.ListValues.StoreListFacade;

public class ListAckReceiver extends Thread{

	private int serverPort = 2043;
	private ServerSocket listenSocket;

	private static ListAckReceiver instance;

	public static ListAckReceiver getInstance(){
		if (instance == null)
			instance = new ListAckReceiver();
		return instance;
	}

	private ListAckReceiver(){
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
				Log.e("ListAckReceiver", result);
				ObjectMapper mapper = new ObjectMapper();
				ListAnswer receivedAnswer = mapper.readValue(result, ListAnswer.class);
				switch (receivedAnswer.getAnswerType()){
					case RFC:
						StoreListFacade.getInstance().getRfcObs().storeRules(receivedAnswer.getAnswerList());
						break;
					case SYMPTOM:
						StoreListFacade.getInstance().getSymptomObs().storeRules(receivedAnswer.getAnswerList());
						break;
					case ACTION:
						StoreListFacade.getInstance().getActionObs().storeRules(receivedAnswer.getAnswerList());
						break;
					case POLICY:
						StoreListFacade.getInstance().getPolicyObs().storeRules(receivedAnswer.getAnswerList());
						break;
					default:
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
