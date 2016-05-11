package frameself.planner;

import frameself.format.Action;
import frameself.format.Attribute;
import frameself.format.Effector;
import frameself.format.Rfc;
import frameself.gui.GuiAdmin;
import frameself.knowledge.KnowledgeManager;
import frameself.main.Admin;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PlannerManager
{
	private RfcCollector rfcCollector;
	private PlanInference planInference;
	private PlanStore planStore;
	ArrayList<java.util.HashMap<String, String>> nonNormalizedRfcs;
	ArrayList<Rfc> rfcs;
	ArrayList<Action> actions;

	public Object[] createRfcObject(Rfc r)
	{
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), r.getId(), r.getCategory(), r.getValue(), r.getLocation(), Integer.valueOf(r.getPriority()), Integer.valueOf(r.getSeverity()), r.getDescription(), r.getTimestamp(), r.getExpiry() };
	}

	public Object[] createActionObject(Action a) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), a.getCategory(), a.getName(), ((Attribute)a.getAttributes().get(0)).getName() + " = " + ((Attribute)a.getAttributes().get(0)).getValue(), Integer.valueOf(a.getPriority()), a.getDescription(), a.getEffector().getName(), a.getTimestamp() };
	}

	public PlannerManager(String planInferenceRule)
	{
		this.rfcs = new ArrayList();

		this.planInference = new PlanInference(planInferenceRule);
		this.planStore = new PlanStore();
		this.nonNormalizedRfcs = new ArrayList();
	}

	public ArrayList<Action> plan(ArrayList<Rfc> rf) {
		this.rfcs = rf;
		System.out.println();
		System.out.println("\nReceived Rfcs: (" + this.rfcs.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Rfc r : PlannerManager.this.rfcs) {
					System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getReceivedRfcs().getModel();
					model.addRow(PlannerManager.this.createRfcObject(r));
				}
				} });
		}
		ArrayList<Effector> effectors = KnowledgeManager.getEffectors();
		ArrayList<frameself.format.Policy> policies = KnowledgeManager.getPolicies();
		this.actions = this.planInference.infer(this.rfcs, effectors, policies);
		System.out.println();
		System.out.println("\nInfered plans:(" + this.actions.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Action a : PlannerManager.this.actions) {
					System.out.println(a.getName() + ", " + ((Attribute)a.getAttributes().get(0)).getName() + ", " + ((Attribute)a.getAttributes().get(0)).getValue() + ", " + a.getEffector().getName() + ", " + a.getTimestamp());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedPlans().getModel();
					model.addRow(PlannerManager.this.createActionObject(a));
				}
				for (Action a : PlannerManager.this.actions) {
					System.out.println(a.getName() + ", " + ((Attribute)a.getAttributes().get(0)).getName() + ", " + ((Attribute)a.getAttributes().get(0)).getValue() + ", " + a.getEffector().getName() + ", " + a.getTimestamp());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getActionsTable().getModel();
					model.addRow(PlannerManager.this.createActionObject(a));
				}
				}
			});
		}
		this.planStore.store(this.actions);
		System.out.println();

		return this.actions;
	}

	public PlanInference getPlanInference(){
		return this.planInference;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/PlannerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */