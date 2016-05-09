package frameself.analyzer;

import frameself.format.Symptom;
import frameself.monitor.SymptomStore;
import java.util.ArrayList;


public class SymptomCollector
implements Runnable
{
	private ArrayList<Symptom> symptoms;

	public void run()
	{
		this.symptoms = new ArrayList();
		for (;;) {
			this.symptoms.addAll(SymptomStore.getSymptoms());
			try {
				Thread.sleep(2000L);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Symptom> getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(ArrayList<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/SymptomCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */