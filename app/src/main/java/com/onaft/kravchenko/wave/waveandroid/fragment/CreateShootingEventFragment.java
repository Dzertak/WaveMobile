package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static final String TAG = "CreateShootingEventFragment";
    private TextView mTextViewPurpose, mTextViewType, mTextViewEvent, mTextViewCustomer,
            mTextViewOperator, mTextViewJurnalist, mTextViewDriver, mTextViewStartDate,
            mTextViewEndDate, mTextViewEventDescription, mTextViewEventAddress;
    private DataManager mDataManager;
    private Contract mContract;
    private Event mEvent;



    public CreateShootingEventFragment() {
        // Required empty public constructor
    }


    public static CreateShootingEventFragment newInstance(Context context, Event event) {
        CreateShootingEventFragment fragment = new CreateShootingEventFragment();
        Bundle args = new Bundle();
        fragment.setContext(context);
        fragment.setEvent(event);
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
        mTextViewEventDescription = getView().findViewById(R.id.textView_view_shooting_event_description);
        mTextViewEndDate = getView().findViewById(R.id.textView_view_shooting_date_end);
        mTextViewStartDate = getView().findViewById(R.id.textView_view_shooting_date_start);
        mTextViewEventAddress = getView().findViewById(R.id.textView_view_shooting_event_address);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shooting_edit: {
                Toast.makeText(mContext, "Редактировать", Toast.LENGTH_SHORT).show();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.shooting_edit, menu);
    }


    public void setContext(Context context) {
        mContext = context;
    }

    public void setEvent(Event event) {
        mEvent = event;
    }

    @Override
    public void onResume() {
        super.onResume();
        dataLoad(String.valueOf(mEvent.getShooting().getId_shooting()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_shooting_event, container, false);
    }

    private void dataLoad(String id_shooting){
        mTextViewEvent.setText(mEvent.getName());
        mTextViewType.setText(mEvent.getShooting().getTypeShooting().getName());
        mTextViewPurpose.setText(mEvent.getShooting().getPurpose());
        mTextViewEventDescription.setText(mEvent.getDescription());
        mTextViewEventAddress.setText(mEvent.getAddress());
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        try {
            mTextViewStartDate.setText(df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").parse(mEvent.getShooting().getDate_start())));
            mTextViewEndDate.setText(df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").parse(mEvent.getShooting().getDate_end())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //mTextViewStartDate.setText(df.format(mEvent.getShooting().getDate_start()));
        //mTextViewEndDate.setText(df.format(mEvent.getShooting().getDate_end()));

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
