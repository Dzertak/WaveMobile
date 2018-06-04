package com.onaft.kravchenko.wave.waveandroid.api;

import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShootingRestService {
    @GET("entities/shootingEvents")
    Call<List<Event>> shootingEventById(@Query("id_employee") String id_employee);

    @GET("entities/shootingEventsAll")
    Call<List<Event>> shootingEventAll();
}
