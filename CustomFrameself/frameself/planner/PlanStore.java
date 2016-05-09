package frameself.planner;

import frameself.format.Action;
import java.util.ArrayList;

public class PlanStore
{
	public static ArrayList<Action> actions = new ArrayList<Action>();


	public void store(ArrayList<Action> actions)
	{
		PlanStore.actions.addAll(actions);
	}

	public static ArrayList<Action> getPlans() {
		return PlanStore.actions;
	}

	public void setActions(ArrayList<Action> actions) {
		PlanStore.actions = actions;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/PlanStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */