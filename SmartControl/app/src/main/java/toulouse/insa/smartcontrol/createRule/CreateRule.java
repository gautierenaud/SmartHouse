package toulouse.insa.smartcontrol.createRule;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import toulouse.insa.smartcontrol.ListValues.StoreListFacade;
import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.communicate.CustomRule;

public class CreateRule extends AppCompatActivity {

    private CheckBox mPolicyCheck;

    // variables for displaying policies
    private ExpandableListView mPolicyList;
    private ExpandableListAdapter policyViewAdapter;

    private TriggerRecyclerAdapter triggerRecyclerAdapter;
    private ActionRecyclerAdapter actionRecyclerAdapter;

    // Data used for the trigger selection
    protected ArrayBlockingQueue<Pair<Integer, String>> selectedTriggers;

    // Data used for the trigger selection
    protected ArrayBlockingQueue<Pair<Integer, String>> selectedActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        selectedTriggers = new ArrayBlockingQueue<>(StoreListFacade.getInstance().getRfcList().size());
        selectedActions = new ArrayBlockingQueue<>(StoreListFacade.getInstance().getActionList().size());

        this.setTitle("Create New Rule");

        /** POLICY DISPLAY **/
        mPolicyCheck = (CheckBox) findViewById(R.id.policy_check);
        if (StoreListFacade.getInstance().getPolicyList().size() == 0)
            mPolicyCheck.setClickable(false);
        mPolicyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mPolicyCheck.isChecked()){
                    mPolicyList.setVisibility(View.VISIBLE);
                } else {
                    mPolicyList.setVisibility(View.GONE);
                }
            }
        });
        mPolicyList = (ExpandableListView) findViewById(R.id.policy_list);

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Choose Policy");
        // init the child items from the Policies from the stored policy list
        HashMap<String, List<String>> childs = new HashMap<>();
        ArrayList<String> childItems = getChilds();
        childs.put(headers.get(0), childItems);

        policyViewAdapter = new TriggerExpandableListAdapter(this, headers, childs);
        mPolicyList.setAdapter(policyViewAdapter);
        mPolicyList.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mPolicyList.collapseGroup(groupPosition);
                TextView selectedOption = (TextView) v.getRootView().findViewById(R.id.choice_result);
                selectedOption.setText(policyViewAdapter.getChild(groupPosition, childPosition).toString());
                return false;
            }
        });
        /** END OF POLICY DISPLAY **/


        /** TRIGGER SELECTION **/
        // initializing the trigger recycler view
        RecyclerView triggerRecyclerView = (RecyclerView) findViewById(R.id.trigger_recycler_view);
        triggerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.triggerRecyclerAdapter = new TriggerRecyclerAdapter(this, this);
        triggerRecyclerView.setAdapter(this.triggerRecyclerAdapter);
        /** END OF TRIGGER SELECTION **/

        /** ACTION SELECTION **/
        // initializing the trigger recycler view
        RecyclerView actionRecyclerView = (RecyclerView) findViewById(R.id.action_recycler_view);
        actionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.actionRecyclerAdapter = new ActionRecyclerAdapter(this, this);
        actionRecyclerView.setAdapter(this.actionRecyclerAdapter);
        /** END OF TRIGGER SELECTION **/
    }

    @NonNull
    private ArrayList<String> getChilds() {
        ArrayList<String> childItems = new ArrayList<>();
        ArrayList<CustomRule> policies = StoreListFacade.getInstance().getPolicyList();
        for (CustomRule r : policies){
            childItems.add(r.getName());
        }
        return childItems;
    }

    public void commitRule(View view){
        // ListAllRules.ruleList.add(new CustomRule(mEditTitle.getText().toString(), mEditTrigger.getText().toString(), mEditAction.getText().toString()));
        finish();
    }

    public void addTriggerSpinner(View view){
        if (this.selectedTriggers.remainingCapacity() > 0)
            this.triggerRecyclerAdapter.addSpinner();
    }

    // return the possibles values for a specific holder
    public ArrayList<String> getPossibleTriggerChoices(int position){
        ArrayList<String> result = new ArrayList<>();
        for (CustomRule rule : StoreListFacade.getInstance().getRfcList()){
            // look if the option is already selected
            boolean alreadySelected = false;
            for (Pair<Integer, String> p : selectedTriggers){
                if (p.first != position && p.second.equals(rule.getName()))
                    alreadySelected = true;
            }
            if (!alreadySelected)
                result.add(rule.getName());
        }
        return result;
    }

    // add the value of a selection when changing the selection of a spinner
    public void updateTriggerSelectionChange(Pair<Integer, String> newSelection){
        for (Pair<Integer, String> p : selectedTriggers){
            if (p.first.equals(newSelection.first))
                selectedTriggers.remove(p);
        }
        selectedTriggers.add(newSelection);
        System.out.println("Change " + newSelection.first);
        printSelectedTriggers();
    }

    // add the value of a selection when deleting a spinner
    public void updateTriggerSelectionDelete(int deletedPosition){
        for (Pair<Integer, String> p : selectedTriggers){
            if (p.first == deletedPosition)
                selectedTriggers.remove(p);
            else if (p.first > deletedPosition){
                selectedTriggers.remove(p);
                selectedTriggers.add(new Pair<>(p.first - 1, p.second));
            }
        }
        printSelectedTriggers();
    }

    public String getTriggerSelection(int position){
        String result = "";
        for (Pair<Integer, String> p : selectedTriggers){
            if (p.first == position)
                result = p.second;
        }
        return result;
    }

    public void printSelectedTriggers(){
        System.out.println("printing selected Triggers");
        for (Pair<Integer, String> p : this.selectedTriggers){
            System.out.println("\t" + p.first + " , " + p.second);
        }
    }

    public void addActionSpinner(View view){
        if (this.selectedActions.remainingCapacity() > 0)
            this.actionRecyclerAdapter.addSpinner();
    }

    // return the possibles values for a specific holder
    public ArrayList<String> getPossibleActionChoices(int position){
        ArrayList<String> result = new ArrayList<>();
        for (CustomRule rule : StoreListFacade.getInstance().getActionList()){
            // look if the option is already selected
            boolean alreadySelected = false;
            for (Pair<Integer, String> p : selectedActions){
                if (p.first != position && p.second.equals(rule.getName()))
                    alreadySelected = true;
            }
            if (!alreadySelected)
                result.add(rule.getName());
        }
        return result;
    }

    // add the value of a selection when changing the selection of a spinner
    public void updateActionSelectionChange(Pair<Integer, String> newSelection){
        for (Pair<Integer, String> p : selectedActions){
            if (p.first.equals(newSelection.first))
                selectedActions.remove(p);
        }
        selectedActions.add(newSelection);
        System.out.println("Change " + newSelection.first);
        printSelectedActions();
    }

    // add the value of a selection when deleting a spinner
    public void updateActionSelectionDelete(int deletedPosition){
        for (Pair<Integer, String> p : selectedActions){
            if (p.first == deletedPosition)
                selectedActions.remove(p);
            else if (p.first > deletedPosition){
                selectedActions.remove(p);
                selectedActions.add(new Pair<>(p.first - 1, p.second));
            }
        }
        printSelectedActions();
    }

    public String getActionSelection(int position){
        String result = "";
        for (Pair<Integer, String> p : selectedActions){
            if (p.first == position)
                result = p.second;
        }
        return result;
    }

    public void printSelectedActions(){
        System.out.println("printing selected Triggers");
        for (Pair<Integer, String> p : this.selectedActions){
            System.out.println("\t" + p.first + " , " + p.second);
        }
    }
}
