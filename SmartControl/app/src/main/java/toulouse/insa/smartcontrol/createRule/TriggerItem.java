package toulouse.insa.smartcontrol.createRule;

/**
 * Created by gautierenaud on 14/05/16.
 */
public class TriggerItem {

    private String name;
    private String param;

    public TriggerItem(String name, String param){
        this.name = name;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
