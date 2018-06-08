package com.onaft.kravchenko.wave.waveandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.onaft.kravchenko.wave.waveandroid.fragment.CreateShootingEventFragment;
import com.onaft.kravchenko.wave.waveandroid.fragment.CreateShootingGroupFragment;
import com.onaft.kravchenko.wave.waveandroid.manage.ConstantManager;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class CreateShootingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private View mProgressView;
    private ArrayList<Integer> mArrayListFlags;
    private DataManager mDataManager;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shooting);
        mArrayListFlags = new ArrayList<>();
        mDataManager = DataManager.getInstance();
        if (mArrayListFlags.size()!=3) {
            Call<List<Employee>> listCall = mDataManager.employeesAll();
            listCall.enqueue(new Callback<List<Employee>>() {
                @Override
                public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                    mArrayListFlags.add(0);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }

                @Override
                public void onFailure(Call<List<Employee>> call, Throwable t) {
                    mArrayListFlags.add(0);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }
            });
            Call<List<Customer>> listCall2 = mDataManager.customersAll();
            listCall2.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                    mArrayListFlags.add(1);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }

                @Override
                public void onFailure(Call<List<Customer>> call, Throwable t) {
                    mArrayListFlags.add(1);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }

            });
            Call<List<TypeShooting>> listCall3 = mDataManager.typeShootingAll();
            listCall3.enqueue(new Callback<List<TypeShooting>>() {
                @Override
                public void onResponse(Call<List<TypeShooting>> call, Response<List<TypeShooting>> response) {
                    mArrayListFlags.add(2);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }

                @Override
                public void onFailure(Call<List<TypeShooting>> call, Throwable t) {
                    mArrayListFlags.add(2);
                    if (mArrayListFlags.size() == 3)
                        mProgressView.setVisibility(GONE);
                }
            });
        }

        mProgressView = findViewById(R.id.login_progress);
        mProgressView.bringToFront();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        toolbar.setNavigationOnClickListener(t -> onBackPressed());
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Не все данные заполнены", Snackbar.LENGTH_LONG)
                        .setAction("Где?", t ->{
                            Toast.makeText(CreateShootingActivity.this, "Здесь!", Toast.LENGTH_SHORT).show();
                        }).show();
            }
        });

    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_shooting, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create_shooting, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SparseArrayCompat<Fragment> mMapTab;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mMapTab = new SparseArrayCompat<>();
            mMapTab.put(ConstantManager.KEY_TAB_ONE,CreateShootingGroupFragment.newInstance(getApplicationContext()));
           // mMapTab.put(ConstantManager.KEY_TAB_TWO, CreateShootingEventFragment.newInstance(getApplicationContext(),));
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return mMapTab.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mMapTab.size();
        }
    }
}
