package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Account {
    @SerializedName("id_account")
    @Expose
    private int id_account;

    @SerializedName("id_employee")
    @Expose
    private int id_employee;

    @SerializedName("id_type_access")
    @Expose
    private int id_type_access;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    public Account(){

    }
    public Account(int id_employee, int id_type_access, String login, String password) {
        this.id_account = id_account;
        this.id_employee = id_employee;
        this.id_type_access = id_type_access;
        this.login = login;
        this.password = password;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_type_access() {
        return id_type_access;
    }

    public void setId_type_access(int id_type_access) {
        this.id_type_access = id_type_access;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id_account=" + id_account +
                ", id_employee=" + id_employee +
                ", id_type_access=" + id_type_access +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
