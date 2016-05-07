package frameself.knowledge;

import frameself.format.Effector;
import java.util.ArrayList;
import java.util.Collection;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;




public class EffectorInit
{
	private StatefulKnowledgeSession ksession;

	public EffectorInit(String ruleName)
	{
		this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
	}

	public ArrayList<Effector> init()
	{
		this.ksession.fireAllRules();

		ArrayList<Effector> output = new ArrayList();
		Collection<Object> objects = this.ksession.getObjects();
		ArrayList<FactHandle> handles = new ArrayList();

		for (Object object : objects) {
			if ((object instanceof Effector)) {
				output.add((Effector)object);
				handles.add(this.ksession.getFactHandle((Effector)object));
			}
		}

		for (FactHandle handle : handles) {
			this.ksession.retract(handle);
		}

		return output;
	}

	public StatefulKnowledgeSession getKsession() {
		return this.ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/knowledge/EffectorInit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */