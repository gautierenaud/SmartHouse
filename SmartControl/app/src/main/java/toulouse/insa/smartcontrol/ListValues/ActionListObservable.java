package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;
import java.util.Observable;

import toulouse.insa.smartcontrol.communicate.CustomRule;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class ActionListObservable extends Observable {

    private ArrayList<CustomRule> actionRules;

    public ActionListObservable(){
        actionRules = new ArrayList<>();
    }

    // replace all the existing rules with the new ones
    public void storeRules(ArrayList<CustomRule> newRules){
        actionRules.clear();
        actionRules.addAll(newRules);
        setChanged();
        notifyObservers();
    }

    // get the rules
    public ArrayList<CustomRule> getActionRules(){
        return actionRules;
    }
}
