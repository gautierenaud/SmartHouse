package toulouse.insa.smartcontrol.viewactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.communicate.CustomRule;

public class CreateRule extends AppCompatActivity {

    private EditText mEditTitle;
    private EditText mEditTrigger;
    private EditText mEditAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Create New Rule");

        mEditTitle = (EditText) findViewById(R.id.edit_title);
        mEditTrigger = (EditText) findViewById(R.id.edit_trigger);
        mEditAction = (EditText) findViewById(R.id.edit_action);
    }

    public void CommitRule(View view){
        // ListAllRules.ruleList.add(new CustomRule(mEditTitle.getText().toString(), mEditTrigger.getText().toString(), mEditAction.getText().toString()));
        finish();
    }
}
