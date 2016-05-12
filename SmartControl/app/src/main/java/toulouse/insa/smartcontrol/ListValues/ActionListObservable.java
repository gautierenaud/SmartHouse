package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;
import java.util.Observable;

import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ReqType;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class ActionListObservable extends Observable {

    private static ArrayList<CustomRule> actionRules;

    public ActionListObservable(){
        actionRules = new ArrayList<>();
    }

    // replace all the existing rules with the new ones
    public void storeRules(ArrayList<CustomRule> newRules){
        actionRules.clear();
        actionRules.addAll(newRules);
        System.out.println("We have now " + actionRules.size() + " action rules stored");
        setChanged();
        notifyObservers();
    }

    // get the rules
    public ArrayList<CustomRule> getActionRules(){
        return actionRules;
    }

    public ReqType getReqType(){
        return ReqType.ACTION;
    }
}
