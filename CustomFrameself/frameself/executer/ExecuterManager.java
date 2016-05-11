package frameself.executer;

import frameself.format.Action;
import frameself.format.Attribute;
import frameself.format.Effector;
import frameself.gui.GuiAdmin;
import frameself.main.Admin;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;






public class ExecuterManager
{
	ActionDispatcher actionDispatcher;
	ActionResultReceiver actionResultReceiver;
	ArrayList<Action> actions;

	public ExecuterManager()
	{
		this.actionDispatcher = new ActionDispatcher();
		this.actionResultReceiver = new ActionResultReceiver();
		new Thread(this.actionResultReceiver).start();
		this.actions = new ArrayList();
	}




	public void execute(ArrayList<Action> ac)
	{
		this.actions = ac;
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Action a : ExecuterManager.this.actions) {
					System.out.println(a.getName() + " " + a.getResult());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getActions().getModel();
					model.addRow(ExecuterManager.this.createActionObject(a));
				}
	
				}
			});
		}
		this.actionDispatcher.dispatch(this.actions);
	}


	public Object[] createActionObject(Action a)
	{
		String parameters = "";
		for (int i = 0; i < a.getAttributes().size(); i++) {
			parameters = 
					parameters + ((Attribute)a.getAttributes().get(i)).getName() + "=" + ((Attribute)a.getAttributes().get(i)).getValue();
			if (i > 0) {
				parameters = parameters + "&";
			}
		}
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), 
				a.getCategory(), a.getName(), parameters, Integer.valueOf(a.getPriority()), 
				a.getDescription(), a.getEffector().getName(), a.getTimestamp() };
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ExecuterManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */