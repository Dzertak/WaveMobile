package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateShootingEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateShootingEventFragment extends Fragment {

    private Context mContext;
    private String id_shooting;
    public static final String TAG = "CreateShootingEventFragment";
    private TextView mTextViewPurpose, mTextViewType, mTextViewEvent, mTextViewCustomer,
            mTextViewOperator, mTextViewJurnalist, mTextViewDriver;
    private DataManager mDataManager;
    private Contract mContract;
    private Event mEvent;



    public CreateShootingEventFragment() {
        // Required empty public constructor
    }


    public static CreateShootingEventFragment newInstance(Context context, String id_shooting) {
        CreateShootingEventFragment fragment = new CreateShootingEventFragment();
        Bundle args = new Bundle();
        fragment.setContext(context);
        fragment.setId_shooting(id_shooting);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDataManager = DataManager.getInstance();


        mTextViewPurpose = getView().findViewById(R.id.textView_view_shooting_purpose);
        mTextViewCustomer = getView().findViewById(R.id.textView_view_shooting_customer);
        mTextViewDriver = getView().findViewById(R.id.textView_view_shooting_driver);
        mTextViewEvent = getView().findViewById(R.id.textView_view_shooting_event);
        mTextViewJurnalist = getView().findViewById(R.id.textView_view_shooting_jurnalist);
        mTextViewType = getView().findViewById(R.id.textView_view_shooting_types);
        mTextViewOperator = getView().findViewById(R.id.textView_view_shooting_operator);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setId_shooting(String id_shooting){
        this.id_shooting = id_shooting;
    }

    @Override
    public void onResume() {
        super.onResume();
        dataLoad();
        List<Event> events = mDataManager.getmPreferencesManager().loadShooting();
        for (Event ev: events)
            if (ev.getId_shooting()==Integer.valueOf(id_shooting))
                mTextViewEvent.setText(ev.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_shooting_event, container, false);
    }

    private void dataLoad(){
        Call<Customer> customerCall = mDataManager.customerByIdShooting(id_shooting);
        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                mTextViewCustomer.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
        Call<Contract> contractCall = mDataManager.contractByIdShooting(id_shooting);
        contractCall.enqueue(new Callback<Contract>() {
            @Override
            public void onResponse(Call<Contract> call, Response<Contract> response) {
                mContract = response.body();
            }

            @Override
            public void onFailure(Call<Contract> call, Throwable t) {

            }
        });
        Call<Shooting> shootingCall = mDataManager.shootingByIdShooting(id_shooting);
        shootingCall.enqueue(new Callback<Shooting>() {
            @Override
            public void onResponse(Call<Shooting> call, Response<Shooting> response) {

                mTextViewPurpose.setText(response.body().getPurpose());
                mTextViewType.setText(response.body().getTypeShooting().getName());
            }

            @Override
            public void onFailure(Call<Shooting> call, Throwable t) {

            }
        });
        Call<List<Employee>> listCall = mDataManager.employeesByIdShooting(id_shooting);
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                for (int i=0; i<response.body().size(); i++){
                    Employee employee = response.body().get(i);
                    if (employee.getPosition()==2){
                        mTextViewOperator.setText(employee.getName());
                    } else if (employee.getPosition()==3) {
                        mTextViewJurnalist.setText(employee.getName());
                    } else if (employee.getPosition()==4){
                        mTextViewDriver.setText(employee.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });
    }

}
