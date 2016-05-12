package toulouse.insa.smartcontrol.ListValues;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Observable;

import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ReqType;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class RfcListObservable extends Observable{

    private ArrayList<CustomRule> rfcRules;

    public RfcListObservable(){
        rfcRules = new ArrayList<>();
    }

    // replace all the existing rules with the new ones
    public void storeRules(ArrayList<CustomRule> newRules){
        rfcRules.clear();
        rfcRules.addAll(newRules);
        setChanged();
        notifyObservers();
    }

    // get the Rfc rules
    public ArrayList<CustomRule> getRfcRules(){
        return rfcRules;
    }

    public ReqType getReqType(){
        return ReqType.RFC;
    }
}
