package com.onaft.kravchenko.wave.waveandroid;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onaft.kravchenko.wave.waveandroid.manage.ConstantManager;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.util.DeleteShootingCallBack;
import com.onaft.kravchenko.wave.waveandroid.util.Wave;
import com.onaft.kravchenko.wave.waveandroid.util.adapter.ShootingAdapter;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements DeleteShootingCallBack, NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private DataManager mDataManager;
    private Account mAccount;
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    private FloatingActionButton mFloatingActionButtonAddShooting;
    private ShootingAdapter adapter;
    private List<Event> mEvents;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static final String TAG = "MainActivity";
    public static final int ADD_SHOOTING_REQUEST = 789;
    private BroadcastReceiver mBroadcastReceiver;


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, new IntentFilter("com.onaft.kravchenko.wave.waveandroid.action.EVENT"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Gson gson = new Gson();
                String json = intent.getStringExtra("com.onaft.kravchenko.wave.waveandroid.broadcast.event");
                Type type = new TypeToken<List<Event>>() {
                }.getType();
                List<Event> events = gson.fromJson(json, type);
                refreshList(events);
            }
        };

        sp = Wave.getSharedPreferences();
        e = sp.edit();
        mFloatingActionButtonAddShooting = findViewById(R.id.fab_add_shooting);

        mDataManager = DataManager.getInstance();

        mEvents = mDataManager.getmPreferencesManager().loadShooting();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_shooting);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        adapter = new ShootingAdapter(mEvents,this,this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_shooting);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        //for nestedscroll on top
        recyclerView.setFocusable(false);
        //search mainLinear layout

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        settingView();
    }

    public void refreshList(List<Event> data) {
        mDataManager.getmPreferencesManager().saveShooting(data);
        adapter.notifyDataSetChanged();

        //This setData from other class
        adapter.setData(data);
    }

    private void settingView(){
        mAccount = mDataManager.getmPreferencesManager().loadAccount();
        if (mAccount != null)
            if (mAccount.getId_type_access()!=1){
                //START SERVICE
                scheduleJob();
                mFloatingActionButtonAddShooting.setVisibility(View.GONE);
            }


        mFloatingActionButtonAddShooting.setOnClickListener(t->{
            Intent intent = new Intent(getApplicationContext(),ShootingActivity.class);
            intent.putExtra("shooting_type", false);
            startActivityForResult(intent,ADD_SHOOTING_REQUEST);
        });


        Call<List<Event>> responseCall = mDataManager.shootingEventById(String.valueOf(mAccount.getId_employee()));
        if (mAccount != null)
            if (mAccount.getId_type_access()==1)
                responseCall = mDataManager.shootingEventAll();
        responseCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                mEvents = response.body();
                refreshList(mEvents);

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Не вышло обновить список съёмок", Toast.LENGTH_SHORT).show();
            }
        });
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

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

        if (id == R.id.nav_shooting) {
            // Handle the camera action
        } else if (id == R.id.nav_send) {
            //hide

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(getApplicationContext(),ToolsActivity.class));
        } else if (id == R.id.nav_exit) {
            e.putBoolean(ConstantManager.HAS_AUTHORIZE,false);
            e.apply();
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {
        Call<List<Event>> responseCall = mDataManager.shootingEventById(String.valueOf(mAccount.getId_employee()));
        if (mAccount != null)
            if (mAccount.getId_type_access()==1)
                responseCall = mDataManager.shootingEventAll();
        responseCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                mEvents = response.body();
                refreshList(mEvents);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Не вышло обновить список съёмок", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_SHOOTING_REQUEST) {
            List<Event> events = mDataManager.getmPreferencesManager().loadShooting();
            adapter.notifyItemInserted(events.size()-1);
            refreshList(events);
        }
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, ShootingSchedulerService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 20 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }

    @Override
    public void deleteShootingCallBack(List<Event> eventList) {
        refreshList(eventList);
    }
}
