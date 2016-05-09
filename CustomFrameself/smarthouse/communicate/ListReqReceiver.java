package smarthouse.communicate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import frameself.analyzer.AnalyzerManager;
import frameself.monitor.MonitorManager;
import frameself.planner.PlannerManager;

public class ListReqReceiver extends Thread{
	
	private int bufferSize = 4000;
	private int serverPort = 2042;
	private ServerSocket listenSocket;
	private PlannerManager plannerManager;
	private AnalyzerManager analyzerManager;
	private MonitorManager monitorManager;
	
	public ListReqReceiver(PlannerManager plan, AnalyzerManager analyzer, MonitorManager monitor){
		this.plannerManager = plan;
		this.analyzerManager = analyzer;
		this.monitorManager = monitor;
		try {
			listenSocket = new ServerSocket(this.serverPort);
			System.out.println("ListReqReceiver starts listening to port " + serverPort);
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
				
				ObjectMapper mapper = new ObjectMapper();
				ReqType req = mapper.readValue(result, ReqType.class);
				ListAckSender sender = new ListAckSender(req, connectedSocket.getInetAddress(), this.plannerManager, this.analyzerManager, this.monitorManager);
				sender.sendAck();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
