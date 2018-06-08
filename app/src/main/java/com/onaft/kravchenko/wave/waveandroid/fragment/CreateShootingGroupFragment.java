package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateShootingGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateShootingGroupFragment extends Fragment {

    public static final String TAG = "CreateShootingGroupFragment";
    private Context mContext;
    private DataManager mDataManager;
    private SearchableSpinner mSearchableSpinnerType, mSearchableSpinnerEvent,
            mSearchableSpinnerCustomer, mSearchableSpinnerOperator, mSearchableSpinnerJurnalist, mSearchableSpinnerDriver ;


    public CreateShootingGroupFragment() {
        // Required empty public constructor
    }

    public static CreateShootingGroupFragment newInstance(Context context) {
        CreateShootingGroupFragment fragment = new CreateShootingGroupFragment();
        Bundle args = new Bundle();
        fragment.setContext(context);
        fragment.setArguments(args);
        return fragment;
    }


    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_shooting_group, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDataManager = DataManager.getInstance();
        mSearchableSpinnerType = getView().findViewById(R.id.searchSpinner_shooting_types);
        mSearchableSpinnerType.setTitle("Select type shooting");
        mSearchableSpinnerType.setPositiveButton("Cancel");

        mSearchableSpinnerCustomer = getView().findViewById(R.id.searchSpinner_shooting_customer);
        mSearchableSpinnerCustomer.setTitle("Select customer");
        mSearchableSpinnerCustomer.setPositiveButton("Cancel");

        mSearchableSpinnerOperator = getView().findViewById(R.id.searchSpinner_shooting_operator);
        mSearchableSpinnerOperator.setTitle("Select operator");
        mSearchableSpinnerOperator.setPositiveButton("Cancel");

        mSearchableSpinnerJurnalist = getView().findViewById(R.id.searchSpinner_shooting_jurnalist);
        mSearchableSpinnerJurnalist.setTitle("Select jurnalist");
        mSearchableSpinnerJurnalist.setPositiveButton("Cancel");

        mSearchableSpinnerDriver = getView().findViewById(R.id.searchSpinner_shooting_driver);
        mSearchableSpinnerDriver.setTitle("Select driver");
        mSearchableSpinnerDriver.setPositiveButton("Cancel");


        mSearchableSpinnerEvent = getView().findViewById(R.id.searchSpinner_shooting_event);
        mSearchableSpinnerEvent.setTitle("Select event");
        mSearchableSpinnerEvent.setPositiveButton("Cancel");



    }

    @Override
    public void onResume() {
        super.onResume();
        loadDate();
    }

    private void loadDate(){


        List<String> eventsTitle = new ArrayList<>();
        List<Event> events = new ArrayList<>(mDataManager.getmPreferencesManager().loadShooting());

        for (int i=0; i<events.size(); i++){
            eventsTitle.add(events.get(i).getName());
        }
        ArrayAdapter<CharSequence> adapterEvents = new ArrayAdapter(mContext,
                R.layout.textview_spinner_item, eventsTitle);
        // Specify layout to be used when list of choices appears
        adapterEvents.setDropDownViewResource(R.layout.textview_spinner_item);
        mSearchableSpinnerEvent.setAdapter(adapterEvents);

        Call<List<Employee>> listCall = mDataManager.employeesAll();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                List<String> operatorTitle = new ArrayList<>();
                List<String> jurnalistTitle = new ArrayList<>();
                List<String> driverTitle = new ArrayList<>();

                for (int i=0; i<response.body().size(); i++){
                    Employee employee = response.body().get(i);
                    if (employee.getPosition()==2){
                        operatorTitle.add(employee.getName());
                    } else if (employee.getPosition()==3) {
                        jurnalistTitle.add(employee.getName());
                    } else if (employee.getPosition()==4){
                        driverTitle.add(employee.getName());
                    }
                }
                ArrayAdapter<CharSequence> adapterOperator = new ArrayAdapter(mContext,
                        R.layout.textview_spinner_item, operatorTitle);
                ArrayAdapter<CharSequence> adapterJurnalist = new ArrayAdapter(mContext,
                        R.layout.textview_spinner_item, jurnalistTitle);
                ArrayAdapter<CharSequence> adapterDriver = new ArrayAdapter(mContext,
                        R.layout.textview_spinner_item, driverTitle);

                // Specify layout to be used when list of choices appears
                adapterOperator.setDropDownViewResource(R.layout.textview_spinner_item);
                adapterJurnalist.setDropDownViewResource(R.layout.textview_spinner_item);
                adapterDriver.setDropDownViewResource(R.layout.textview_spinner_item);
                mSearchableSpinnerOperator.setAdapter(adapterOperator);
                mSearchableSpinnerJurnalist.setAdapter(adapterJurnalist);
                mSearchableSpinnerDriver.setAdapter(adapterDriver);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });

        Call<List<Customer>> listCall2 = mDataManager.customersAll();
        listCall2.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                List<String> eventsTitle = new ArrayList<>();
                // List<Event> events = new ArrayList<>(mDataManager.getmPreferencesManager().loadShooting());

                for (int i=0; i<response.body().size(); i++){
                    eventsTitle.add(response.body().get(i).getName());
                }
                ArrayAdapter<CharSequence> adapterEvents = new ArrayAdapter(mContext,
                        R.layout.textview_spinner_item, eventsTitle);
                // Specify layout to be used when list of choices appears
                adapterEvents.setDropDownViewResource(R.layout.textview_spinner_item);
                mSearchableSpinnerCustomer.setAdapter(adapterEvents);


            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

            }

        });
        Call<List<TypeShooting>> listCall3 = mDataManager.typeShootingAll();
        listCall3.enqueue(new Callback<List<TypeShooting>>() {
            @Override
            public void onResponse(Call<List<TypeShooting>> call, Response<List<TypeShooting>> response) {
                List<String> eventsTitle = new ArrayList<>();
                // List<Event> events = new ArrayList<>(mDataManager.getmPreferencesManager().loadShooting());

                for (int i=0; i<response.body().size(); i++){
                    eventsTitle.add(response.body().get(i).getName());
                }
                ArrayAdapter<CharSequence> adapterEvents = new ArrayAdapter(mContext,
                        R.layout.textview_spinner_item, eventsTitle);
                // Specify layout to be used when list of choices appears
                adapterEvents.setDropDownViewResource(R.layout.textview_spinner_item);
                mSearchableSpinnerType.setAdapter(adapterEvents);

            }

            @Override
            public void onFailure(Call<List<TypeShooting>> call, Throwable t) {

            }
        });
    }
}
