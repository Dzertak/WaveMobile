package com.onaft.kravchenko.wave.waveandroid.manage;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Customer;
import com.onaft.kravchenko.wave.waveandroid.model.Employee;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.model.TypeShooting;
import com.onaft.kravchenko.wave.waveandroid.util.Wave;

import java.lang.reflect.Type;
import java.util.List;

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

    public void saveShooting(List<Event> data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_SHOOTING, json);
        editor.apply();
    }

    public List<Event> loadShooting() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_SHOOTING, "");
        Type type = new TypeToken<List<Event>>() {
        }.getType();
        List<Event> events = gson.fromJson(json, type);
        return events;
    }

    public void saveEmployees(List<Employee> data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_EMPLOYEE, json);
        editor.apply();
    }

    public List<Employee> loadEmployees() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_EMPLOYEE, "");
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> events = gson.fromJson(json, type);
        return events;
    }

    public void saveCustomers(List<Customer> data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_CUSTOMER, json);
        editor.apply();
    }

    public List<Customer> loadCustomers() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_CUSTOMER, "");
        Type type = new TypeToken<List<Customer>>() {
        }.getType();
        List<Customer> events = gson.fromJson(json, type);
        return events;
    }

    public void saveTypesShootng(List<TypeShooting> data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(ConstantManager.KEY_TYPE_SHOOTING, json);
        editor.apply();
    }

    public List<TypeShooting> loadTypeShooting() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(ConstantManager.KEY_TYPE_SHOOTING, "");
        Type type = new TypeToken<List<TypeShooting>>() {
        }.getType();
        List<TypeShooting> events = gson.fromJson(json, type);
        return events;
    }

}
