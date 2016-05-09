package frameself.monitor;

import frameself.format.Event;
import frameself.format.Symptom;
import frameself.knowledge.KnowledgeManager;
import java.util.ArrayList;
import java.util.Collection;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;




public class SymptomInference
{
	private StatefulKnowledgeSession ksession;

	public SymptomInference(String ruleName)
	{
		this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
	}

	public ArrayList<Symptom> infer(ArrayList<Event> events)
	{
		ArrayList<FactHandle> eventHandles = new ArrayList();
		for (Event event : events) {
			FactHandle eventhandle = this.ksession.insert(event);
			eventHandles.add(eventhandle);
		}
		this.ksession.fireAllRules();

		for (FactHandle eventHandle : eventHandles) {
			this.ksession.retract(eventHandle);
		}

		ArrayList<Symptom> symptoms = new ArrayList();
		Object objects = this.ksession.getObjects();
		ArrayList<FactHandle> symptomHandles = new ArrayList();

		for (Object object : (Collection)objects) {
			if ((object instanceof Symptom)) {
				symptoms.add((Symptom)object);
				symptomHandles.add(this.ksession.getFactHandle((Symptom)object));
			}
		}

		for (FactHandle symptomHandle : symptomHandles) {
			this.ksession.retract(symptomHandle);
		}

		return symptoms;
	}

	public StatefulKnowledgeSession getKsession() {
		return this.ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
}


/* Location:home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/SymptomInference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */