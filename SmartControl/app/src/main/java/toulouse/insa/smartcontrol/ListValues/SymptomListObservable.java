package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;
import java.util.Observable;

import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ReqType;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class SymptomListObservable extends Observable {

    private ArrayList<CustomRule> symptomRules;

    public SymptomListObservable(){
        symptomRules = new ArrayList<>();
    }

    // replace all the existing rules with the new ones
    public void storeRules(ArrayList<CustomRule> newRules){
        symptomRules.clear();
        symptomRules.addAll(newRules);
        setChanged();
        notifyObservers();
    }

    // get the Symptom rules
    public ArrayList<CustomRule> getSymptomRules(){
        return symptomRules;
    }

    public ReqType getReqType(){
        return ReqType.SYMPTOM;
    }
}
