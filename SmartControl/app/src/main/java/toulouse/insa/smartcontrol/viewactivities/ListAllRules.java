package toulouse.insa.smartcontrol.viewactivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.util.Date;

import frameself.Dispatcher;
import frameself.format.Event;
import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.common.FrameselfObject;
import toulouse.insa.smartcontrol.common.MyRecyclerAdapter;
import toulouse.insa.smartcontrol.communicate.ListAckReceiver;
import toulouse.insa.smartcontrol.communicate.ListReqSender;
import toulouse.insa.smartcontrol.communicate.MultipurposeCollector;
import toulouse.insa.smartcontrol.communicate.ReqType;
import toulouse.insa.smartcontrol.communicate.SpecificCollector;

public class ListAllRules extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<FrameselfObject> frameselfList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter;

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
                // test for sending event
                Event event = new Event();
                event.setCategory("LampControl");
                event.setValue("420");
                event.setSensor("Phone");
                event.setLocation("Home");
                event.setTimestamp(new Date());
                event.setExpiry(new Date(System.currentTimeMillis()+20000));
                MultipurposeCollector.eventQueue.add(event);

                ListReqSender.addReq(ReqType.ACTION);

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
        // filling for test purpose
        for (int i = 0; i < 20; i++){
            frameselfList.add(new FrameselfObject("rule" + Integer.toString(i), "trigger", "action"));
        }

        // initialize the recyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyRecyclerAdapter(frameselfList);
        mRecyclerView.setAdapter(mAdapter);

        ListAckReceiver receiver = new ListAckReceiver();
        receiver.start();

        ListReqSender sender = new ListReqSender();
        sender.start();

        new Thread(){
            public void run(){
                MultipurposeCollector.start();
            }
        }.start();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_symptom) {
            // Handle the camera action
        } else if (id == R.id.nav_rfc) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {
            openSettings();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openSettings(){
        Intent intent = new Intent(ListAllRules.this, ChangeParams.class);
        startActivity(intent);
    }

    public ArrayList<FrameselfObject> getFrameselfList() {
        return frameselfList;
    }
}
