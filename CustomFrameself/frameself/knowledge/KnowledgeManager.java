package frameself.knowledge;

import frameself.format.Effector;
import frameself.format.Policy;
import frameself.format.Sensor;
import frameself.gui.GuiAdmin;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;


public class KnowledgeManager
{
	private static ArrayList<Sensor> sensors;
	private static ArrayList<Policy> policies;
	private static ArrayList<Effector> effectors;

	public KnowledgeManager(String sensorInitRule, String effectorInitRule, String policyInitRule)
	{
		SensorInit sensorInit = new SensorInit(sensorInitRule);
		EffectorInit effectorInit = new EffectorInit(effectorInitRule);
		PolicyInit policyInit = new PolicyInit(policyInitRule);
		sensors = sensorInit.init();
		effectors = effectorInit.init();
		policies = policyInit.init();
		updateGui();
	}

	public void updateGui()
	{
		for (Sensor s : sensors) {
			DefaultTableModel model = (DefaultTableModel)GuiAdmin.getSensors().getModel();
			model.addRow(new Object[] { s.getName() });
		}

		for (Effector e : effectors) {
			DefaultTableModel model = (DefaultTableModel)GuiAdmin.getEffectors().getModel();
			model.addRow(new Object[] { e.getName() });
		}

		for (Policy p : policies) {
			DefaultTableModel model = (DefaultTableModel)GuiAdmin.getPolicies().getModel();
			model.addRow(new Object[] { p.getName() });
		}
	}


	public static StatefulKnowledgeSession createStatefullKnowledge(String ruleName)
	{
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(ruleName), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		return ksession;
	}


	public static ArrayList<Effector> getEffectors()
	{
		return effectors;
	}

	public static void setEffectors(ArrayList<Effector> effectors) {
		effectors = effectors;
	}

	public static ArrayList<Sensor> getSensors()
	{
		return sensors;
	}

	public static void setSensors(ArrayList<Sensor> sensors)
	{
		sensors = sensors;
	}

	public static ArrayList<Policy> getPolicies()
	{
		return policies;
	}

	public static void setPolicies(ArrayList<Policy> policies)
	{
		policies = policies;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/knowledge/KnowledgeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */