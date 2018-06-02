package com.onaft.kravchenko.wave.waveandroid.manage;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.util.Wave;

import java.lang.reflect.Type;

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        this.mSharedPreferences = Wave.getSharedPreferences();
    }

    public void saveAccount(Account data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_ACCOUNT, json);
        editor.apply();
    }

    public Account loadAccount() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_ACCOUNT, "");
        Type type = new TypeToken<Account>() {
        }.getType();
        Account account = gson.fromJson(json, type);
        return account;
    }

    public void saveEmployee(Employee data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_EMPLOYEE, json);
        editor.apply();
    }

    public Employee loadEmployee() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_EMPLOYEE, "");
        Type type = new TypeToken<Employee>() {
        }.getType();
        Employee employee = gson.fromJson(json, type);
        return employee;
    }
}
