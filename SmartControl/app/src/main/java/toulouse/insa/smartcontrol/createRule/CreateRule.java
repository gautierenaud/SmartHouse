package toulouse.insa.smartcontrol.createRule;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import toulouse.insa.smartcontrol.ListValues.StoreListFacade;
import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.communicate.CustomRule;

public class CreateRule extends AppCompatActivity {

    private EditText mEditTitle;
    private EditText mEditTrigger;
    private EditText mEditAction;
    private CheckBox mPolicyCheck;

    private ExpandableListView mPolicyList;
    private ExpandableListAdapter policyViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Create New Rule");

        mEditTitle = (EditText) findViewById(R.id.edit_title);
        mPolicyCheck = (CheckBox) findViewById(R.id.policy_check);
        mPolicyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mPolicyCheck.isChecked()){
                    // populate the List
                    ArrayList<CustomRule> policies = StoreListFacade.getInstance().getPolicyList();
                    mPolicyList.setVisibility(View.VISIBLE);
                } else {
                    mPolicyList.setVisibility(View.GONE);
                }
            }
        });
        mPolicyList = (ExpandableListView) findViewById(R.id.policy_list);

        // initialize the headers
        // in this case, we will only have one
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Choose Policy");
        // init the child items from the Policies from the stored policy list
        HashMap<String, List<String>> childs = new HashMap<>();
        ArrayList<String> childItems = new ArrayList<>();
        ArrayList<CustomRule> policies = StoreListFacade.getInstance().getPolicyList();
        for (CustomRule r : policies){
            childItems.add(r.getName());
        }
        childs.put(headers.get(0), childItems);

        policyViewAdapter = new ParamExpandableListAdapter(this, headers, childs);
        mPolicyList.setAdapter(policyViewAdapter);
        mPolicyList.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mPolicyList.collapseGroup(groupPosition);
                TextView selectedOption = (TextView) mPolicyList.getRootView().findViewById(R.id.choice_result);
                selectedOption.setText(policyViewAdapter.getChild(groupPosition, childPosition).toString());
                return false;
            }
        });
    }

    public void CommitRule(View view){
        // ListAllRules.ruleList.add(new CustomRule(mEditTitle.getText().toString(), mEditTrigger.getText().toString(), mEditAction.getText().toString()));
        finish();
    }

    public void AddTrigger(View view){
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.param_rule_group, null);

        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.trigger_rule_container);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
