package frameself.analyzer;

import frameself.format.Rfc;
import frameself.format.Symptom;
import frameself.gui.GuiAdmin;
import frameself.main.Admin;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class AnalyzerManager
{
	private RfcInference rfcInference;
	private RfcStore rfcStore;
	ArrayList<HashMap<String, String>> nonNormalizedSymptoms;
	ArrayList<Symptom> symptoms;
	ArrayList<Rfc> rfcs;

	public Object[] createSymptomObject(Symptom s)
	{
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), s.getId(), s.getCategory(), s.getValue(), s.getLocation(), Integer.valueOf(s.getPriority()), Integer.valueOf(s.getSeverity()), s.getDescription(), s.getTimestamp(), s.getExpiry() };
	}

	public Object[] createRfcObject(Rfc r) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), r.getId(), r.getCategory(), r.getValue(), r.getLocation(), Integer.valueOf(r.getPriority()), Integer.valueOf(r.getSeverity()), r.getDescription(), r.getTimestamp(), r.getExpiry() };
	}



	public AnalyzerManager(String rfcInferenceRule)
	{
		this.rfcInference = new RfcInference(rfcInferenceRule);
		this.rfcStore = new RfcStore();
		this.symptoms = new ArrayList();
		this.nonNormalizedSymptoms = new ArrayList();
	}

	public ArrayList<Rfc> analyze(ArrayList<Symptom> symps)
	{
		this.symptoms = symps;

		System.out.println();
		System.out.println("\nReceived Symptoms: (" + this.symptoms.size() + ")");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { for (Symptom s : AnalyzerManager.this.symptoms) {
				System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
				DefaultTableModel model = (DefaultTableModel)GuiAdmin.getReceivedSymptoms().getModel();
				model.addRow(AnalyzerManager.this.createSymptomObject(s));
			}
			}
		});
		this.rfcs = this.rfcInference.infer(this.symptoms);
		System.out.println();
		System.out.println("\nInfered rfcs:(" + this.rfcs.size() + ")");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { for (Rfc r : AnalyzerManager.this.rfcs) {
				System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
				DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedRfcs().getModel();
				model.addRow(AnalyzerManager.this.createRfcObject(r));
			}

			for (Rfc r : AnalyzerManager.this.rfcs) {
				System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
				DefaultTableModel model = (DefaultTableModel)GuiAdmin.getRfcsTable().getModel();
				model.addRow(AnalyzerManager.this.createRfcObject(r));
			}
			} });
		this.rfcStore.store(this.rfcs);
		System.out.println();

		return this.rfcs;
	}
	
	public RfcInference getRfcInference(){
		return this.rfcInference;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/AnalyzerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */