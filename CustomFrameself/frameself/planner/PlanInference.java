package frameself.planner;

import frameself.format.Action;
import frameself.format.Effector;
import frameself.format.Policy;
import frameself.format.Rfc;
import frameself.knowledge.KnowledgeManager;
import java.util.ArrayList;
import java.util.Collection;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public class PlanInference
{
	private StatefulKnowledgeSession ksession;

	public PlanInference(String ruleName)
	{
		this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
	}

	public ArrayList<Action> infer(ArrayList<Rfc> rfcs, ArrayList<Effector> effectors, ArrayList<Policy> policies)
	{
		ArrayList<FactHandle> rfcHandles = new ArrayList();
		FactHandle rfchandle;
		for (Rfc rfc : rfcs) {
			rfchandle = this.ksession.insert(rfc);
			rfcHandles.add(rfchandle);
		}

		ArrayList<FactHandle> effectorHandles = new ArrayList();
		FactHandle effectorhandle;
		for (Effector effector : effectors) {
			effectorhandle = this.ksession.insert(effector);
			effectorHandles.add(effectorhandle);
		}

		Object policyHandles = new ArrayList();

		for (Policy policy : policies) {
			FactHandle policyHandle = this.ksession.insert(policy);
			((ArrayList)policyHandles).add(policyHandle);
		}

		this.ksession.fireAllRules();

		for (FactHandle rfcHandle : rfcHandles) {
			this.ksession.retract(rfcHandle);
		}

		ArrayList<Action> actions = new ArrayList();
		Collection<Object> objects = this.ksession.getObjects();
		ArrayList<FactHandle> actionHandles = new ArrayList();

		for (Object object : objects) {
			if ((object instanceof Action)) {
				actions.add((Action)object);
				actionHandles.add(this.ksession.getFactHandle((Action)object));
			}
		}

		for (FactHandle actionHandle : actionHandles) {
			this.ksession.retract(actionHandle);
		}

		for (FactHandle effectorHandle : effectorHandles) {
			this.ksession.retract(effectorHandle);
		}

		for (FactHandle policyHandle : (ArrayList<FactHandle>) policyHandles) {
			this.ksession.retract(policyHandle);
		}

		return actions;
	}

	public StatefulKnowledgeSession getKsession() {
		return this.ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/PlanInference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */