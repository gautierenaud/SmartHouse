package toulouse.insa.smartcontrol.common;

/**
 * Created by gautierenaud on 28/04/16.
 */

// basic object class to represent a frameself instance
public class FrameselfObject {

    private String mTitle;
    private String mTrigger;
    private String mAction;

    public FrameselfObject(String title, String trigger, String action){
        this.mTitle = title;
        this.mTrigger = trigger;
        this.mAction = action;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTrigger() {
        return mTrigger;
    }

    public void setmTrigger(String mTrigger) {
        this.mTrigger = mTrigger;
    }

    public String getmAction() {
        return mAction;
    }

    public void setmAction(String mAction) {
        this.mAction = mAction;
    }
}
