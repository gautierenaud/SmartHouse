package toulouse.insa.smartcontrol.viewRules;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import toulouse.insa.smartcontrol.ListValues.StoreListFacade;
import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.communicate.CustomRule;
import toulouse.insa.smartcontrol.communicate.ListAckReceiver;
import toulouse.insa.smartcontrol.communicate.ListReqSender;
import toulouse.insa.smartcontrol.communicate.MultipurposeCollector;
import toulouse.insa.smartcontrol.communicate.ReqType;
import toulouse.insa.smartcontrol.createRule.CreateRule;
import toulouse.insa.smartcontrol.params.ChangeParams;
import toulouse.insa.smartcontrol.voiceRecognition.PocketSphinxActivity;

public class ListAllRules extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer {

    private enum DisplayOption {ALL, SYMPTOMS, RFCS, ACTIONS, POLICIES};

    // a fusion of the different arrays
    public static ArrayList<RuleToDisplay> ruleList = new ArrayList<>();

    // lists to store each types of rules/policies
    private ArrayList<CustomRule> actionList = new ArrayList<>();
    private ArrayList<CustomRule> rfcList = new ArrayList<>();
    private ArrayList<CustomRule> symptomList = new ArrayList<>();
    private ArrayList<CustomRule> policyList = new ArrayList<>();

    private DisplayOption displayOption = DisplayOption.ALL;

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter;
    private ListReqSender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_rules);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListAllRules.this, CreateRule.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* CardView for Frameself Objects */

        // initialize the recyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyRecyclerAdapter(ruleList);
        mRecyclerView.setAdapter(mAdapter);

        // add itself to the list of observable of all the rule types
        StoreListFacade.getInstance().getRfcObs().addObserver(this);
        StoreListFacade.getInstance().getActionObs().addObserver(this);
        StoreListFacade.getInstance().getSymptomObs().addObserver(this);
        StoreListFacade.getInstance().getPolicyObs().addObserver(this);

        if (!ListAckReceiver.getInstance().isAlive())
            ListAckReceiver.getInstance().start();

        sender = new ListReqSender();
        sender.start();

        new Thread(){
            public void run(){
                MultipurposeCollector.start();
            }
        }.start();

        // send the different requests for the rule lists
        sender.sendAllReq();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_all_rules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        } else if (id == R.id.action_refresh){
            sender.sendAllReq();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            if (this.displayOption != DisplayOption.ALL) {
                this.displayOption = DisplayOption.ALL;
                updateAdapter();
            }
        } else if (id == R.id.nav_symptoms) {
            if (this.displayOption != DisplayOption.SYMPTOMS) {
                this.displayOption = DisplayOption.SYMPTOMS;
                updateAdapter();
            }
        } else if (id == R.id.nav_rfcs) {
            if (this.displayOption != DisplayOption.RFCS) {
                this.displayOption = DisplayOption.RFCS;
                updateAdapter();
            }
        } else if (id == R.id.nav_actions) {
            if (this.displayOption != DisplayOption.ACTIONS) {
                this.displayOption = DisplayOption.ACTIONS;
                updateAdapter();
            }
        } else if (id == R.id.nav_policies) {
            if (this.displayOption != DisplayOption.POLICIES) {
                this.displayOption = DisplayOption.POLICIES;
                updateAdapter();
            }
        }else if (id == R.id.nav_vocal) {
            Intent intent = new Intent(ListAllRules.this, PocketSphinxActivity.class);
            startActivity(intent);


            /*
            Intent intent = new Intent(ListAllRules.this, VoiceRecorder.class);
            startActivity(intent);
            */
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openSettings(){
        Intent intent = new Intent(ListAllRules.this, ChangeParams.class);
        startActivity(intent);
    }

    public ArrayList<RuleToDisplay> getFrameselfList() {
        return ruleList;
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d("ListAllRules", "modification on object" + observable.getClass());

        /** we swap the content of the lists **/
        this.actionList.clear();
        this.actionList.addAll(StoreListFacade.getInstance().getActionList());

        this.rfcList.clear();
        this.rfcList.addAll(StoreListFacade.getInstance().getRfcList());

        this.symptomList.clear();
        this.symptomList.addAll(StoreListFacade.getInstance().getSymptomList());

        this.policyList.clear();
        this.policyList.addAll(StoreListFacade.getInstance().getPolicyList());

        updateAdapter();
    }

    public void updateAdapter(){
        this.ruleList.clear();
        /** add the rules to the ruleList **/
        switch (this.displayOption){
            case ALL:
                for (CustomRule r : this.symptomList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.SYMPTOM));
                }

                for (CustomRule r : this.rfcList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.RFC));
                }

                for (CustomRule r : this.actionList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.ACTION));
                }

                for (CustomRule r : this.policyList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.POLICY));
                }
                break;
            case SYMPTOMS:
                for (CustomRule r : this.symptomList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.SYMPTOM));
                }
                break;
            case RFCS:
                for (CustomRule r : this.rfcList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.RFC));
                }
                break;
            case ACTIONS:
                for (CustomRule r : this.actionList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.ACTION));
                }
                break;
            case POLICIES:
                for (CustomRule r : this.policyList){
                    this.ruleList.add(new RuleToDisplay(r, ReqType.POLICY));
                }
                break;
        }

        // update the UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.swapAdapter(new MyRecyclerAdapter(ListAllRules.ruleList), false);
            }
        });
    }
}
