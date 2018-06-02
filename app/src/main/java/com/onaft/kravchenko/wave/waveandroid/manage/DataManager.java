package com.onaft.kravchenko.wave.waveandroid.manage;

import com.onaft.kravchenko.wave.waveandroid.api.AuthorizationRestService;
import com.onaft.kravchenko.wave.waveandroid.api.ServiceConnector;
import com.onaft.kravchenko.wave.waveandroid.model.Account;

import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;
    private PreferencesManager mPreferencesManager;
    private AuthorizationRestService mRestService;

    private DataManager() {
        this.mPreferencesManager = new PreferencesManager();
        this.mRestService = ServiceConnector.createService(AuthorizationRestService.class);
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

}
