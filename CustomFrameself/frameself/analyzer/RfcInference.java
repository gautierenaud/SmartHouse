package frameself.analyzer;

import frameself.format.Rfc;
import frameself.format.Symptom;
import frameself.knowledge.KnowledgeManager;
import java.util.ArrayList;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public class RfcInference
{
	private StatefulKnowledgeSession ksession;

	public RfcInference(String ruleName)
	{
		this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
	}

	public ArrayList<Rfc> infer(ArrayList<Symptom> symptoms)
	{
		ArrayList<FactHandle> symptomHandles = new ArrayList();
		for (Symptom symptom : symptoms) {
			FactHandle symptomhandle = this.ksession.insert(symptom);
			symptomHandles.add(symptomhandle);
		}
		this.ksession.fireAllRules();

		for (FactHandle symptomHandle : symptomHandles) {
			this.ksession.retract(symptomHandle);
		}

		ArrayList<Rfc> rfcs = new ArrayList();
		Object objects = this.ksession.getObjects();
		ArrayList<FactHandle> rfcHandles = new ArrayList();

		for (Object object : (java.util.Collection)objects) {
			if ((object instanceof Rfc)) {
				rfcs.add((Rfc)object);
				rfcHandles.add(this.ksession.getFactHandle((Rfc)object));
			}
		}

		for (FactHandle rfcHandle : rfcHandles) {
			this.ksession.retract(rfcHandle);
		}

		return rfcs;
	}

	public StatefulKnowledgeSession getKsession() {
		return this.ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/RfcInference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */