package toulouse.insa.smartcontrol.createRule;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import toulouse.insa.smartcontrol.communicate.CustomRule;

/**
 * Created by gautierenaud on 16/05/16.
 */
public class DummyParameters {

    public static String[] dummyRfcs = new String[]{
            "SwitchOnLEDIn",
            "SwitchOnLEDOut",
            "SwitchOffLights",
            "SwitchOffLED",
            "SwitchOnLights",
            "SwitchOnLightsRed"
    };

    public static String[] dummyActions = new String[]{
            "changeColor",
            "setText"
    };

    public static ArrayList<CustomRule> rfcsToCustomRule(){
        ArrayList<CustomRule> result = new ArrayList<>();
        for (String s : dummyRfcs){
            CustomRule tmpRule = new CustomRule();
            tmpRule.setId(s);
            tmpRule.setName(s);
            result.add(tmpRule);
        }
        return result;
    }

    public static ArrayList<CustomRule> actionsToCustomRule(){
        ArrayList<CustomRule> result = new ArrayList<>();
        for (String s : dummyActions){
            CustomRule tmpRule = new CustomRule();
            tmpRule.setId(s);
            tmpRule.setName(s);
            result.add(tmpRule);
        }
        return result;
    }

}
