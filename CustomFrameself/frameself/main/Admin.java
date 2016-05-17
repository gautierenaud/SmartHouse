package frameself.main;

import frameself.analyzer.AnalyzerManager;
import frameself.executer.ExecuterManager;
import frameself.gui.GuiAdmin;
import frameself.knowledge.KnowledgeManager;
import frameself.monitor.MonitorManager;
import frameself.planner.PlannerManager;
import smarthouse.communicate.ListReqReceiver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;

public class Admin
{
	MonitorManager monitorManager;
	AnalyzerManager analyzerManager;
	PlannerManager plannerManager;
	ExecuterManager executerManager;
	KnowledgeManager knowledgeManager;
	Loop loop;
	Thread thread;
	public static final String NOM_RESSOURCES = "config.properties";
	public static Properties prop;
	public static boolean useGUI = false;
	static int loopCounter = 0;

	// handmade argument for sleeping sleepTime * seconds (1 sec by default)
	public static int sleepTime = 5;

	public static void main(String[] args) {
		if(args.length>0)
		{
			useGUI = true;
		}
		new Admin();
	}

	public Admin()
	{
		try {
			prop = new Properties();
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (useGUI){
			GuiAdmin gui = new GuiAdmin(this);
			gui.start();
		}
		this.knowledgeManager = new KnowledgeManager(prop.getProperty("sensor_init_rule"), prop.getProperty("effector_init_rule"), prop.getProperty("policy_init_rule"));
		this.monitorManager = new MonitorManager(prop.getProperty("event_formatter_rule"), prop.getProperty("event_filter_rule"), prop.getProperty("event_agregator_rule"), prop.getProperty("symptom_inference_rule"));
		this.analyzerManager = new AnalyzerManager(prop.getProperty("rfc_inference_rule"));
		this.plannerManager = new PlannerManager(prop.getProperty("action_inference_rule"));
		this.executerManager = new ExecuterManager();
		this.loop = new Loop(this.monitorManager, this.analyzerManager, this.plannerManager, this.executerManager);
		
		// lancement d'un thread pour récupérer les listes
		new ListReqReceiver(this.plannerManager, this.analyzerManager, this.monitorManager);

		this.start();
	}

	public void start() {
		this.thread = new Thread(this.loop);
		this.thread.start();
	}

	public void stop() {
		this.loop.stop();
	}

	public static String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static int getLoopCounter() {
		return loopCounter;
	}

	public static void setLoopCounter(int loopCounter) {
		Admin.loopCounter = loopCounter;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/main/Admin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */