package com.onaft.kravchenko.wave.waveandroid.api;

import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Contract;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.Shooting;
import com.onaft.kravchenko.wave.waveandroid.model.ShootingGroup;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;
import com.onaft.kravchenko.wave.waveandroid.util.ShootingGroupRequest;
import com.onaft.kravchenko.wave.waveandroid.util.WorkRating;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("entities/findEvents")
    Call<List<Event>> findEvents();

    @GET("entities/customersAll")
    Call<List<Customer>> customersAll();

    @GET("entities/employeesAll")
    Call<List<Employee>> employeesAll();

    @GET("entities/typeShootingAll")
    Call<List<TypeShooting>> typeShootingAll();

    @POST("entities/addCustomer")
    Call<Customer> addCustomer(@Body Customer customer);


    @POST("entities/addContract")
    Call<Contract> addContract(@Body Contract contract);

    @POST("entities/addEvent")
    Call<Event> addEvent(@Body Event event);

    @POST("entities/addShooting")
    Call<Shooting> addShooting(@Body Shooting shooting);

    @POST("entities/addShootingGroup")
    Call<String> addShootingGroup(@Body ShootingGroupRequest groupRequest);

    @DELETE("entities/deleteShooting")
    Call<String> deleteShooting(@Query("id_shooting") String id_shooting);

    @GET("entities/findWorkRating")
    Call<List<WorkRating>> findWorkRating();
}
