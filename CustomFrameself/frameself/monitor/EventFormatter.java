package frameself.monitor;

import frameself.format.Event;
import frameself.knowledge.KnowledgeManager;
import java.util.ArrayList;
import java.util.Collection;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;





public class EventFormatter
{
	private StatefulKnowledgeSession ksession;

	public EventFormatter(String ruleName)
	{
		this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
	}

	public ArrayList<Event> format(ArrayList<Event> nonNormalizedEvents) {
		ArrayList<FactHandle> nonNormalizedEventHandles = new ArrayList();
		for (Event nonNormalizedEvent : nonNormalizedEvents) {
			this.ksession.insert(nonNormalizedEvent);
			nonNormalizedEventHandles.add(this.ksession.getFactHandle(nonNormalizedEvent));
		}

		this.ksession.fireAllRules();

		ArrayList<Event> normalizedEvents = new ArrayList();
		Object objects = this.ksession.getObjects();
		ArrayList<FactHandle> normalizedEventHandles = new ArrayList();

		for (Object object : (Collection)objects) {
			if ((object instanceof Event)) {
				normalizedEvents.add((Event)object);
				normalizedEventHandles.add(this.ksession.getFactHandle((Event)object));
			}
		}

		for (FactHandle nonNormalizedEventHandle : nonNormalizedEventHandles) {
			this.ksession.retract(nonNormalizedEventHandle);
		}

		for (FactHandle normalizedEventHandle : normalizedEventHandles) {
			this.ksession.retract(normalizedEventHandle);
		}

		return normalizedEvents;
	}

	public StatefulKnowledgeSession getKsession() {
		return this.ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */