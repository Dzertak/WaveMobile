package com.onaft.kravchenko.wave.waveandroid.manage;

import com.onaft.kravchenko.wave.waveandroid.api.AuthorizationRestService;
import com.onaft.kravchenko.wave.waveandroid.api.ServiceConnector;
import com.onaft.kravchenko.wave.waveandroid.api.ShootingRestService;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;
import com.onaft.kravchenko.wave.waveandroid.util.ShootingGroupRequest;
import com.onaft.kravchenko.wave.waveandroid.util.WorkRating;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;
    private PreferencesManager mPreferencesManager;
    private AuthorizationRestService mRestService;
    private ShootingRestService mShootingRestService;

    private DataManager() {
        this.mPreferencesManager = new PreferencesManager();
        this.mRestService = ServiceConnector.createService(AuthorizationRestService.class);
        this.mShootingRestService = ServiceConnector.createService(ShootingRestService.class);
    }

    public static DataManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getmPreferencesManager() {
        return mPreferencesManager;
    }


    // ================= Network ====================


    public Call<Account> authorize(String login, String password){
        return mRestService.authorization(login,password);
    }

    public Call<List<Event>> shootingEventById(String id_employee){
        return mShootingRestService.shootingEventById(id_employee);
    }

    public Call<List<Employee>> employeesByIdShooting(String id_shooting){
        return mShootingRestService.employeesByIdShooting(id_shooting);
    }

    public Call<Customer> customerByIdShooting(String id_shooting){
        return mShootingRestService.customerByIdShooting(id_shooting);
    }
    public Call<Contract> contractByIdShooting(String id_shooting){
        return mShootingRestService.contractByIdShooting(id_shooting);
    }
    public Call<Shooting> shootingByIdShooting(String id_shooting){
        return mShootingRestService.shootingByIdShooting(id_shooting);
    }

    public Call<List<Event>> shootingEventAll(){
        return mShootingRestService.shootingEventAll();
    }

    public Call<List<Event>> findEvents(){
        return mShootingRestService.findEvents();
    }

    public Call<List<Customer>> customersAll(){
        return mShootingRestService.customersAll();
    }
    public Call<List<Employee>> employeesAll(){
        return mShootingRestService.employeesAll();
    }
    public Call<List<TypeShooting>> typeShootingAll(){
        return mShootingRestService.typeShootingAll();
    }

    public Call<Customer> addCustomer(Customer customer){
        return mShootingRestService.addCustomer(customer);
    }

    public Call<Contract> addContract(Contract contract){
        return mShootingRestService.addContract(contract);
    }

    public Call<Event> addEvent(Event event){
        return mShootingRestService.addEvent(event);
    }

    public Call<Shooting> addShooting(Shooting shooting){
        return mShootingRestService.addShooting(shooting);
    }

    public Call<String> addShootingGroup(ShootingGroupRequest groupRequest){
        return mShootingRestService.addShootingGroup(groupRequest);
    }

    public Call<String> deleteShooting(String id_shooting){
        return mShootingRestService.deleteShooting(id_shooting);
    }

    public Call<List<WorkRating>> findWorkRating(){
        return mShootingRestService.findWorkRating();
    }
}
