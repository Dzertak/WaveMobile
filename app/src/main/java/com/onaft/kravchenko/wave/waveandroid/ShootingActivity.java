package com.onaft.kravchenko.wave.waveandroid;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.onaft.kravchenko.wave.waveandroid.fragment.CreateShootingEventFragment;
import com.onaft.kravchenko.wave.waveandroid.fragment.CreateShootingGroupFragment;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class ShootingActivity extends AppCompatActivity {

    private View mProgressView;
    private ArrayList<Integer> mArrayListFlags;
    private DataManager mDataManager;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooting);
        setupActionBar();
        mFloatingActionButton = findViewById(R.id.fab_shooting_edit);


        mArrayListFlags = new ArrayList<>();
        mDataManager = DataManager.getInstance();
       // loadDate();

        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean key = getIntent().getBooleanExtra("shooting_type", false);
        String id_shooting = getIntent().getStringExtra("id_shooting");

        if (!key){
            fragmentManager.beginTransaction().add(R.id.container_shooting,
                    CreateShootingGroupFragment.newInstance(this), CreateShootingGroupFragment.TAG).commit();
            mFloatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.check));
        } else {
            fragmentManager.beginTransaction().add(R.id.container_shooting,
                    CreateShootingEventFragment.newInstance(this, id_shooting), CreateShootingEventFragment.TAG).commit();
            mFloatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.pencil));
        }





    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
