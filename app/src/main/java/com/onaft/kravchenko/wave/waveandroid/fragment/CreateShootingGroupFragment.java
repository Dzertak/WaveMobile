package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;
import com.onaft.kravchenko.wave.waveandroid.util.ShootingGroupRequest;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
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
    private List<Event> mEvents;
    private List<Customer> mCustomers;
    private boolean period;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;
    private Calendar dateStart, dateEnd, mCalendarTag;
    private SimpleDateFormat dateFormatter;
    private SearchableSpinner mSearchableSpinnerType, mSearchableSpinnerEvent,
            mSearchableSpinnerOperator, mSearchableSpinnerJurnalist, mSearchableSpinnerDriver,
            mSearchableSpinnerCustomer ;
    private TextView mTextViewEventDescription, mTextViewStartDate, mTextViewEndDate;
    private FloatingActionButton mFloatingActionButtonEvent, mFloatingActionButtonCustomer;
    private AlertDialog alertDialog, mAlertDialogCustomer;
    private List<String> eventsTitleCustomer;
    private ProgressDialog progress;
    private MultiAutoCompleteTextView mCompleteTextViewCustomerName, mCompleteTextViewCustomerPhone,
    mCompleteTextViewCustomerAddress, mCompleteTextViewEventName, mCompleteTextViewEventDescription,
    mCompleteTextViewEventAddress;
    private AutoCompleteTextView mCompleteTextViewShootingPurpose;
    private List<TypeShooting> mTypeShootings;
    private List<Employee> mEmployeesOperator, mEmployeesJurnalist, mEmployeesDriver;


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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shooting_save: {
               // Toast.makeText(mContext, "Сохранено", Toast.LENGTH_SHORT).show();
               saveShooting();
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
        inflater.inflate(R.menu.shooting_save, menu);
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
        dateFormatter = new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());
        mTextViewStartDate = getView().findViewById(R.id.textView_shooting_date_start);
        mFloatingActionButtonEvent = getView().findViewById(R.id.fab_shooting_event);
        mCompleteTextViewShootingPurpose = getView().findViewById(R.id.textView_shooting_purpose);
        eventsTitleCustomer = new ArrayList<>();
        mEmployeesOperator = new ArrayList<>();
        mEmployeesJurnalist = new ArrayList<>();
        mEmployeesDriver = new ArrayList<>();

        progress=new ProgressDialog(mContext);
        progress.setMessage("Add record");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);

        mFloatingActionButtonEvent.setOnClickListener(t -> {
            alertDialog.show();
            refreshListCustomer(mCustomers);
        });
        dateStart = Calendar.getInstance();
        dateEnd = Calendar.getInstance();

        //settings datetime picker
        setDateTimeField();
        //timePicker();

        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_add_event, null);
        View customerView = li.inflate(R.layout.dialog_add_customer, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(mContext);
        AlertDialog.Builder mDialogBuilderCustomer = new AlertDialog.Builder(mContext);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);
        mDialogBuilderCustomer.setView(customerView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        //final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        mCompleteTextViewEventName = promptsView.findViewById(R.id.multiAutoCompleteTextView_shooting_event_add_name);
        mCompleteTextViewEventDescription = promptsView.findViewById(R.id.multiAutoCompleteTextView_shooting_event_add_description);
        mCompleteTextViewEventAddress = promptsView.findViewById(R.id.multiAutoCompleteTextView_shooting_event_add_address);

        mFloatingActionButtonCustomer = promptsView.findViewById(R.id.fab_shooting_customer_add);
        mFloatingActionButtonCustomer.setOnClickListener(t->{
            mAlertDialogCustomer.show();
        });


        mSearchableSpinnerCustomer = promptsView.findViewById(R.id.searchSpinner_shooting_customer);
        mSearchableSpinnerCustomer.setTitle("Select customer");
        mSearchableSpinnerCustomer.setPositiveButton("Cancel");


        mCompleteTextViewCustomerName = customerView.findViewById(R.id.multiAutoCompleteTextView_shooting_customer_add_name);
        mCompleteTextViewCustomerPhone = customerView.findViewById(R.id.multiAutoCompleteTextView_shooting_customer_add_phone);
        mCompleteTextViewCustomerAddress = customerView.findViewById(R.id.multiAutoCompleteTextView_shooting_customer_add_address);
        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                progress.show();
                                Event event = new Event();
                                event.setName(mCompleteTextViewEventName.getText().toString());
                                event.setDescription(mCompleteTextViewEventDescription.getText().toString());
                                event.setAddress(mCompleteTextViewEventAddress.getText().toString());
                                event.setId_customer(mCustomers.get(mSearchableSpinnerCustomer.getSelectedItemPosition()).getId_customer());
                                Call<Event> eventCall = mDataManager.addEvent(event);
                                eventCall.enqueue(new Callback<Event>() {
                                    @Override
                                    public void onResponse(Call<Event> call, Response<Event> response) {
                                        progress.dismiss();
                                        dialog.cancel();
                                        mEvents.add(response.body());
                                        refreshListEvent(mEvents);
                                    }

                                    @Override
                                    public void onFailure(Call<Event> call, Throwable t) {
                                        progress.dismiss();
                                        Toast.makeText(mContext, "Не удалось добавить Событие!", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        mDialogBuilderCustomer
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                progress.show();
                                Customer customer = new Customer();
                                customer.setId_customer(0);
                                customer.setName(mCompleteTextViewCustomerName.getText().toString());
                                customer.setPhone(mCompleteTextViewCustomerPhone.getText().toString());
                                customer.setAdress(mCompleteTextViewCustomerAddress.getText().toString());
                                Call<Customer> customerCall = mDataManager.addCustomer(customer);
                                customerCall.enqueue(new Callback<Customer>() {
                                    @Override
                                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                                        progress.dismiss();
                                        dialog.cancel();
                                        mCustomers.add(response.body());
                                        refreshListCustomer(mCustomers);
                                    }

                                    @Override
                                    public void onFailure(Call<Customer> call, Throwable t) {
                                        progress.dismiss();
                                        Toast.makeText(mContext, "Не удалось добавить Клиента!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        alertDialog = mDialogBuilder.create();
        mAlertDialogCustomer = mDialogBuilderCustomer.create();



        mTextViewStartDate.setOnClickListener(t -> {
            period = true;
            fromDatePickerDialog.show();
        });
        mTextViewEndDate = getView().findViewById(R.id.textView_shooting_date_end);
        mTextViewEndDate.setOnClickListener(t -> {
            period = false;
            fromDatePickerDialog.show();
        });
        mDataManager = DataManager.getInstance();
        mTextViewEventDescription = getView().findViewById(R.id.textView_shooting_event_description);
        mSearchableSpinnerType = getView().findViewById(R.id.searchSpinner_shooting_types);
        mSearchableSpinnerType.setTitle("Select type shooting");
        mSearchableSpinnerType.setPositiveButton("Cancel");

        /*mSearchableSpinnerCustomer = getView().findViewById(R.id.searchSpinner_shooting_customer);
        mSearchableSpinnerCustomer.setTitle("Select customer");
        mSearchableSpinnerCustomer.setPositiveButton("Cancel");*/

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
        mSearchableSpinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewEventDescription.setText(mEvents.get(position).getDescription());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loadDate();
    }

    private void loadDate(){




        Call<List<Event>> listCall22 = mDataManager.findEvents();
        listCall22.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                refreshListEvent(response.body());
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

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
                        mEmployeesOperator.add(employee);
                        operatorTitle.add(employee.getName());
                    } else if (employee.getPosition()==3) {
                        mEmployeesJurnalist.add(employee);
                        jurnalistTitle.add(employee.getName());
                    } else if (employee.getPosition()==4){
                        mEmployeesDriver.add(employee);
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
                refreshListCustomer(response.body());
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
                mTypeShootings = response.body();
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

    private void setDateTimeField() {

        mCalendarTag = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(mContext, R.style.AppTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                timePicker(year,  monthOfYear,  dayOfMonth);
                fromTimePickerDialog.show();


            }
        }, mCalendarTag.get(Calendar.YEAR), mCalendarTag.get(Calendar.MONTH), mCalendarTag.get(Calendar.DAY_OF_MONTH));
    }

    private void timePicker(int year, int monthOfYear, int dayOfMonth){
         fromTimePickerDialog = new TimePickerDialog(mContext, R.style.AppTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (period){
                    dateStart.set(year,monthOfYear,dayOfMonth, hourOfDay,minute);
                    if (dateEnd!=null){
                        if (dateStart.getTime().compareTo(dateEnd.getTime()) > 0){
                            Toast.makeText(mContext, "Конечная дата меньше начальной", Toast.LENGTH_SHORT).show();
                        } else {
                            mTextViewStartDate.setText(dateFormatter.format(dateStart.getTime()));
                        }
                    } else {
                        mTextViewStartDate.setText(dateFormatter.format(dateStart.getTime()));
                    }

                } else {
                    dateEnd.set(year,monthOfYear,dayOfMonth,hourOfDay,minute);
                    if (dateStart!=null){
                        if (dateStart.getTime().compareTo(dateEnd.getTime()) > 0){
                            Toast.makeText(mContext, "Конечная дата меньше начальной", Toast.LENGTH_SHORT).show();
                        } else {
                            mTextViewEndDate.setText(dateFormatter.format(dateEnd.getTime()));
                        }
                    } else {
                        mTextViewEndDate.setText(dateFormatter.format(dateEnd.getTime()));
                    }
                }

            }
        }, mCalendarTag.get(Calendar.HOUR_OF_DAY),mCalendarTag.get(Calendar.MINUTE), DateFormat.is24HourFormat(mContext));

    }

    private void refreshListCustomer(List<Customer> customers){
        mCustomers = customers;
        eventsTitleCustomer = new ArrayList<>();
        for (int i=0; i<customers.size(); i++){
            eventsTitleCustomer.add(customers.get(i).getName());
        }
        ArrayAdapter<CharSequence> adapterEvents = new ArrayAdapter(mContext,
                R.layout.textview_spinner_item, eventsTitleCustomer);
        adapterEvents.setDropDownViewResource(R.layout.textview_spinner_item);
        mSearchableSpinnerCustomer.setAdapter(adapterEvents);
    }

    private void refreshListEvent(List<Event> events){
        List<String> eventsTitle = new ArrayList<>();
        mEvents = new ArrayList<>(events);

        for (int i=0; i<events.size(); i++){
            eventsTitle.add(events.get(i).getName());
        }
        ArrayAdapter<CharSequence> adapterEvents = new ArrayAdapter(mContext,
                R.layout.textview_spinner_item, eventsTitle);
        // Specify layout to be used when list of choices appears
        adapterEvents.setDropDownViewResource(R.layout.textview_spinner_item);
        mSearchableSpinnerEvent.setAdapter(adapterEvents);
    }

    private void saveShooting(){
        progress.show();
        Shooting shooting = new Shooting();
        shooting.setPurpose(mCompleteTextViewShootingPurpose.getText().toString());
        shooting.setId_shooting(0);
        TypeShooting typeShooting = new TypeShooting();
        typeShooting.setId_type_shooting(mTypeShootings.get(mSearchableSpinnerType.getSelectedItemPosition()).getId_type_shooting());
        typeShooting.setName(mTypeShootings.get(mSearchableSpinnerType.getSelectedItemPosition()).getName());
        shooting.setTypeShooting(typeShooting);
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        shooting.setDate_start(df.format(new Timestamp(dateStart.getTime().getTime())));
        shooting.setDate_end(df.format(new Timestamp(dateEnd.getTime().getTime())));

        Call<Shooting> shootingCall = mDataManager.addShooting(shooting);
        shootingCall.enqueue(new Callback<Shooting>() {
            @Override
            public void onResponse(Call<Shooting> call, Response<Shooting> response) {
                List<Employee> employees = new ArrayList<>();
                employees.add(mEmployeesOperator.get(mSearchableSpinnerOperator.getSelectedItemPosition()));
                employees.add(mEmployeesJurnalist.get(mSearchableSpinnerJurnalist.getSelectedItemPosition()));
                employees.add(mEmployeesDriver.get(mSearchableSpinnerDriver.getSelectedItemPosition()));
                Shooting shooting1 = response.body();
                ShootingGroupRequest groupRequest = new ShootingGroupRequest(shooting1.getId_shooting(),employees);
                Call<String> bodyCall = mDataManager.addShootingGroup(groupRequest);
                bodyCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            Contract contract = new Contract(0,
                                    mEvents.get(mSearchableSpinnerEvent.getSelectedItemPosition()).getId_event(),
                                    shooting1.getId_shooting());
                            Call<Contract> contractCall = mDataManager.addContract(contract);
                            contractCall.enqueue(new Callback<Contract>() {
                                @Override
                                public void onResponse(Call<Contract> call, Response<Contract> response) {
                                    progress.dismiss();
                                    List<Event> events = mDataManager.getmPreferencesManager().loadShooting();
                                    Event event = mEvents.get(mSearchableSpinnerEvent.getSelectedItemPosition());
                                    event.setShooting(shooting1);
                                    events.add(event);
                                    mDataManager.getmPreferencesManager().saveShooting(events);
                                    Intent intent = new Intent();
                                    getActivity().setResult(RESULT_OK, intent);
                                    getActivity().finish();
                                }

                                @Override
                                public void onFailure(Call<Contract> call, Throwable t) {
                                    Toast.makeText(mContext, "Не удалось добавить Контракт!", Toast.LENGTH_SHORT).show();
                                    Call<String> stringCall = mDataManager.deleteShooting(String.valueOf(shooting1.getId_shooting()));
                                    stringCall.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Toast.makeText(mContext, "Попробуйте ещё раз", Toast.LENGTH_SHORT).show();
                                            progress.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            progress.dismiss();
                                            Toast.makeText(mContext, "Не вышло удалить Съёмку Группы", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mContext, "Не удалось добавить Съёмочную группу!", Toast.LENGTH_SHORT).show();
                        Call<String> stringCall = mDataManager.deleteShooting(String.valueOf(shooting1.getId_shooting()));
                        stringCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                progress.dismiss();
                                Toast.makeText(mContext, "Попробуйте ещё раз", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                progress.dismiss();
                                Toast.makeText(mContext, "Не вышло удалить Съёмку Группы", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

            }

            @Override
            public void onFailure(Call<Shooting> call, Throwable t) {
                Toast.makeText(mContext, "Не удалось добавить Съёмку!", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
