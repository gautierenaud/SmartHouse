package toulouse.insa.smartcontrol.ListValues;

import java.util.ArrayList;

import toulouse.insa.smartcontrol.communicate.CustomRule;

/**
 * Created by gautierenaud on 11/05/16.
 */
public class StoreListFacade {

    private static StoreListFacade instance = null;

    public static StoreListFacade getInstance(){
        if (instance == null)
            instance = new StoreListFacade();
        return instance;
    }

    private static ActionListObservable actionObs;
    private static SymptomListObservable symptomObs;
    private static RfcListObservable rfcObs;
    private static PolicyListObservable policyObs;

    private StoreListFacade(){
        actionObs = new ActionListObservable();
        symptomObs = new SymptomListObservable();
        rfcObs = new RfcListObservable();
        policyObs = new PolicyListObservable();
    }

    public ActionListObservable getActionObs(){
        return actionObs;
    }

    public ArrayList<CustomRule> getActionList(){
        return actionObs.getActionRules();
    }

    public SymptomListObservable getSymptomObs(){
        return symptomObs;
    }

    public ArrayList<CustomRule> getSymptomList(){
        return symptomObs.getSymptomRules();
    }

    public RfcListObservable getRfcObs(){
        return rfcObs;
    }

    public ArrayList<CustomRule> getRfcList(){
        return rfcObs.getRfcRules();
    }

    public ArrayList<CustomRule> getPolicyList(){
        return policyObs.getPolicyList();
    }

    public PolicyListObservable getPolicyObs(){
        return policyObs;
    }
}
