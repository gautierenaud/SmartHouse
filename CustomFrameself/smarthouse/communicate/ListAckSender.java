package smarthouse.communicate;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;

import com.fasterxml.jackson.databind.ObjectMapper;

import frameself.analyzer.AnalyzerManager;
import frameself.format.Policy;
import frameself.knowledge.KnowledgeManager;
import frameself.monitor.MonitorManager;
import frameself.planner.PlannerManager;
import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ListAnswer;
import toulouse.insa.smartcontrol.communicate.ReqType;

public class ListAckSender {
	
	private Socket clientSocket;
	private BufferedOutputStream sWriter;
	private ReqType req;
	private InetAddress destinationAddress;
	private PlannerManager plannerManager;
	private AnalyzerManager analyzerManager;
	private MonitorManager monitorManager;
	
	public ListAckSender(ReqType req, InetAddress dest, PlannerManager plan, AnalyzerManager analyzer, MonitorManager monitor) {
		this.destinationAddress = dest;
		this.plannerManager = plan;
		this.analyzerManager = analyzer;
		this.monitorManager = monitor;
		this.req = req;
		try {
			System.out.println(destinationAddress);
			this.clientSocket = new Socket(destinationAddress, 2043);
			this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendAck(){
		// creating the arrayList
		ArrayList<CustomRule> resultList = new ArrayList<>();
		Collection<KnowledgePackage> knowledgePackages;
		switch (this.req) {
		case SYMPTOM:
			knowledgePackages =  this.monitorManager.getSymptomInference().getKsession().getKnowledgeBase().getKnowledgePackages();
			for (KnowledgePackage p : knowledgePackages){
				Collection<Rule> rules = p.getRules();
				for (Rule r : rules){
					resultList.add(CustomAdaptator.adaptRule(r));
					System.err.println(r.toString() + "/" + r.getId() + "/map size : " + r.getMetaData().size());
					Map<String, Object> map = r.getMetaData();
					for (Map.Entry<String, Object> entry : map.entrySet()){
						System.err.println(entry.getKey() + "/" + entry.getValue());
					}
				}
			}
			break;
		case RFC:
			knowledgePackages =  this.analyzerManager.getRfcInference().getKsession().getKnowledgeBase().getKnowledgePackages();
			for (KnowledgePackage p : knowledgePackages){
				Collection<Rule> rules = p.getRules();
				for (Rule r : rules){
					resultList.add(CustomAdaptator.adaptRule(r));
					System.err.println(r.toString() + "/" + r.getId() + "/map size : " + r.getMetaData().size());
					Map<String, Object> map = r.getMetaData();
					for (Map.Entry<String, Object> entry : map.entrySet()){
						System.err.println(entry.getKey() + "/" + entry.getValue());
					}
				}
			}
			break;
		case ACTION:
			knowledgePackages =  this.plannerManager.getPlanInference().getKsession().getKnowledgeBase().getKnowledgePackages();
			for (KnowledgePackage p : knowledgePackages){
				Collection<Rule> rules = p.getRules();
				for (Rule r : rules){
					
					resultList.add(CustomAdaptator.adaptRule(r));
					System.err.println(r.toString() + "/" + r.getId() + "/map size : " + r.getMetaData().size());
					Map<String, Object> map = r.getMetaData();
					for (Map.Entry<String, Object> entry : map.entrySet()){
						System.err.println(entry.getKey() + "/" + entry.getValue());
					}
				}
			}
			break;
		case POLICY:
			ArrayList<Policy> policies = KnowledgeManager.getPolicies();
			System.out.println("Policy size: " + policies.size());
			for (Policy p : policies){
				System.err.println(p.toString() + "/" + p.getName());
				resultList.add(new CustomRule(p.getName(), null, p.getName()));
			}
		default:
			break;
		}
		
		ListAnswer answer = new ListAnswer(req, resultList);
		
		// mapping the object to Json String and sending it
		ObjectMapper mapper = new ObjectMapper();
		try {
			String sendString = mapper.writeValueAsString(answer);
			sWriter.write(sendString.getBytes(), 0, sendString.getBytes().length);
			sWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
