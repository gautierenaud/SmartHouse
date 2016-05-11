package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;

import toulouse.insa.smartcontrol.communicate.CustomRule;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class StoreListFacade {

    private static ActionListObservable actionObs = new ActionListObservable();
    private static SymptomListObservable symptomObs = new SymptomListObservable();
    private static RfcListObservable rfcObs = new RfcListObservable();

    public static ActionListObservable getActionObs(){
        return actionObs;
    }

    public static ArrayList<CustomRule> getActionList(){
        return actionObs.getActionRules();
    }

    public static SymptomListObservable getSymptomObs(){
        return symptomObs;
    }

    public static ArrayList<CustomRule> getSymptomList(){
        return symptomObs.getSymptomRules();
    }

    public static RfcListObservable getRfcObs(){
        return rfcObs;
    }

    public static ArrayList<CustomRule> getRfcList(){
        return rfcObs.getRfcRules();
    }
}
