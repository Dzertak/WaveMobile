package com.onaft.kravchenko.wave.waveandroid.api;

import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShootingRestService {
    @GET("entities/shootingEvents")
    Call<List<Event>> shootingEventById(@Query("id_employee") String id_employee);

    @GET("entities/employeesByIdShooting")
    Call<List<Employee>> employeesByIdShooting(@Query("id_shooting") String id_shooting);

    @GET("entities/contractByIdShooting")
    Call<Contract> contractByIdShooting(@Query("id_shooting") String id_shooting);

    @GET("entities/customerByIdShooting")
    Call<Customer> customerByIdShooting(@Query("id_shooting") String id_shooting);

    @GET("entities/shootingByIdShooting")
    Call<Shooting> shootingByIdShooting(@Query("id_shooting") String id_shooting);

    @GET("entities/shootingEventsAll")
    Call<List<Event>> shootingEventAll();

    @GET("entities/customersAll")
    Call<List<Customer>> customersAll();

    @GET("entities/employeesAll")
    Call<List<Employee>> employeesAll();

    @GET("entities/typeShootingAll")
    Call<List<TypeShooting>> typeShootingAll();
}
