package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Employee {
    @SerializedName("id_employee")
    @Expose
    private int id_employee;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code_pas")
    @Expose
    private String code_pas;
    @SerializedName("code_ident")
    @Expose
    private String code_ident;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("id_position")
    @Expose
    private int id_position;

    private int work_interest;

    public Employee(int id_employee, String name, String code_pas, String code_ident, String phone, String address, int id_position) {
        this.id_employee = id_employee;
        this.name = name;
        this.code_pas = code_pas;
        this.code_ident = code_ident;
        this.phone = phone;
        this.address = address;
        this.id_position = id_position;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_pas() {
        return code_pas;
    }

    public void setCode_pas(String code_pas) {
        this.code_pas = code_pas;
    }

    public String getCode_ident() {
        return code_ident;
    }

    public void setCode_ident(String code_ident) {
        this.code_ident = code_ident;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPosition() {
        return id_position;
    }

    public void setPosition(int position) {
        this.id_position = position;
    }

    public int getWork_interest() {
        return work_interest;
    }

    public void setWork_interest(int work_interest) {
        this.work_interest = work_interest;
    }

    @Override
    public String toString() {
        return name+" "+String.valueOf(100-work_interest)+"%";

    }
}
