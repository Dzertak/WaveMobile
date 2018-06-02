package com.onaft.kravchenko.wave.waveandroid.util;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Wave  extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    public static SharedPreferences sharedPreferences;


    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}