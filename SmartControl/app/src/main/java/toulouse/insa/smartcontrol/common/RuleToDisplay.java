package toulouse.insa.smartcontrol.common;

import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ReqType;

/**
 * Created by gautierenaud on 12/05/16.
 */
public class RuleToDisplay {

    private ReqType ruleType;
    private String title;

    public RuleToDisplay(CustomRule rule, ReqType type){
        this.title = rule.getId();
        this.ruleType = type;
    }

    public ReqType getRuleType() {
        return ruleType;
    }

    public void setRuleType(ReqType ruleType) {
        this.ruleType = ruleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
