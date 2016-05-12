package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;
import java.util.Observable;

import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ReqType;

/**
 * Created by gautierenaud on 12/05/16.
 */
public class PolicyListObservable extends Observable{

    private static ArrayList<CustomRule> policyList;

    public PolicyListObservable(){
        policyList = new ArrayList<>();
    }

    // replace all the existing rules with the new ones
    public void storeRules(ArrayList<CustomRule> newRules){
        policyList.clear();
        policyList.addAll(newRules);
        System.out.println("We have now " + policyList.size() + " policies stored");
        setChanged();
        notifyObservers();
    }

    // get the rules
    public ArrayList<CustomRule> getPolicyList(){
        return policyList;
    }

    public ReqType getReqType(){
        return ReqType.POLICY;
    }
}
