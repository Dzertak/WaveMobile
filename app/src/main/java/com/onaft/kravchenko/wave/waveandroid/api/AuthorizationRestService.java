package com.onaft.kravchenko.wave.waveandroid.api;

import com.onaft.kravchenko.wave.waveandroid.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthorizationRestService {
    @GET("entities/authorize")
    Call<Account> authorization(@Query("login") String login, @Query("password") String password);
}
