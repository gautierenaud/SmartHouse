package frameself.monitor;

import frameself.format.Event;
import frameself.format.Symptom;
import frameself.gui.GuiAdmin;
import frameself.main.Admin;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class MonitorManager
{
	private EventCollector eventCollector;
	private EventFormatter eventFormatter;
	private EventFilter eventFilter;
	private EventAgregator eventAgregator;
	private SymptomInference symptomInference;
	private SymptomStore symptomStore;
	ArrayList<Event> events;
	ArrayList<Symptom> symptoms;

	public MonitorManager(String eventNormalizerRule, String eventFilterRule, String eventAgregatorRule, String symptomInferenceRule)
	{
		this.eventCollector = new EventCollector();
		new Thread(this.eventCollector).start();
		this.eventFormatter = new EventFormatter(eventNormalizerRule);
		this.eventFilter = new EventFilter(eventFilterRule);
		this.eventAgregator = new EventAgregator(eventAgregatorRule);
		this.symptomInference = new SymptomInference(symptomInferenceRule);
		this.symptomStore = new SymptomStore();
		this.events = new ArrayList();
		this.symptoms = new ArrayList();
	}

	public Object[] createEventObject(Event e) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), e.getTimestamp(), e.getExpiry() };
	}

	public Object[] createSymptomObject(Symptom s) {
		return new Object[] { Integer.valueOf(Admin.getLoopCounter()), s.getId(), s.getCategory(), s.getValue(), s.getLocation(), Integer.valueOf(s.getPriority()), Integer.valueOf(s.getSeverity()), s.getDescription(), s.getTimestamp(), s.getExpiry() };
	}




	public ArrayList<Symptom> monitor()
	{
		this.events = EventCollector.getEvents();

		this.events = this.eventFormatter.format(this.events);

		System.out.println();
		System.out.println("\nNormalized Events: (" + this.events.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Event e : MonitorManager.this.events) {
					System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
	
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getNormalizedEvents().getModel();
					model.addRow(MonitorManager.this.createEventObject(e));
				}
				} });
		}
		this.events = this.eventFilter.filter(this.events);

		System.out.println();
		System.out.println("\nFiltered Events: (" + this.events.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Event e : MonitorManager.this.events) {
					System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getFiltredEvents().getModel();
					model.addRow(MonitorManager.this.createEventObject(e));
				}
				} });
		}
		this.events = this.eventAgregator.agregate(this.events);

		System.out.println();
		System.out.println("\nAgregated Events: (" + this.events.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Event e : MonitorManager.this.events) {
					System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getAgregatedEvents().getModel();
					model.addRow(MonitorManager.this.createEventObject(e));
				}
				for (Event e : MonitorManager.this.events) {
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getEventsTable().getModel();
					model.addRow(MonitorManager.this.createEventObject(e));
				}
				} });
		}
		System.out.println(this.events);
		this.symptoms = this.symptomInference.infer(this.events);
		System.out.println();
		System.out.println("\nInfered symptoms:(" + this.symptoms.size() + ")");
		if (Admin.useGUI){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { for (Symptom s : MonitorManager.this.symptoms) {
					System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedSymptoms().getModel();
					model.addRow(MonitorManager.this.createSymptomObject(s));
				}
	
				for (Symptom s : MonitorManager.this.symptoms) {
					System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
					DefaultTableModel model = (DefaultTableModel)GuiAdmin.getSymptomsTable().getModel();
					model.addRow(MonitorManager.this.createSymptomObject(s));
				}
				}
			});
		}
		this.symptomStore.store(this.symptoms);
		System.out.println();

		EventCollector.getEvents().clear();
		return this.symptoms;
	}

	public EventCollector getEventCollector() {
		return this.eventCollector;
	}

	public void setEventCollector(EventCollector eventCollector) {
		this.eventCollector = eventCollector;
	}

	public EventFilter getEventFilter() {
		return this.eventFilter;
	}

	public void setEventFilter(EventFilter eventFilter) {
		this.eventFilter = eventFilter;
	}

	public EventAgregator getEventAgregator() {
		return this.eventAgregator;
	}

	public void setEventAgregator(EventAgregator eventAgregator) {
		this.eventAgregator = eventAgregator;
	}

	public SymptomInference getSymptomInference() {
		return this.symptomInference;
	}

	public void setSymptomInference(SymptomInference symptomInference) {
		this.symptomInference = symptomInference;
	}

	public SymptomStore getSymptomStore() {
		return this.symptomStore;
	}

	public void setSymptomStore(SymptomStore symptomStore) {
		this.symptomStore = symptomStore;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/MonitorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */