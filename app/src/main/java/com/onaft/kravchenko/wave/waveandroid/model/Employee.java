package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Employee {
    @SerializedName("id_employee")
    @Expose
    private int id_employee;
    @SerializedName("date_reg")
    @Expose
    private Date date_reg;
    @SerializedName("date_birthday")
    @Expose
    private Date date_birthday;
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

    public Employee(int id_employee, Date date_reg, Date date_birthday, String name, String code_pas, String code_ident, String phone, String address, int position) {
        this.id_employee = id_employee;
        this.date_reg = date_reg;
        this.date_birthday = date_birthday;
        this.name = name;
        this.code_pas = code_pas;
        this.code_ident = code_ident;
        this.phone = phone;
        this.address = address;
        this.id_position = position;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public Date getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(Date date_reg) {
        this.date_reg = date_reg;
    }

    public Date getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(Date date_birthday) {
        this.date_birthday = date_birthday;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id_employee +
                ", date_reg=" + date_reg +
                ", date_birthday=" + date_birthday +
                ", name='" + name + '\'' +
                ", code_pas='" + code_pas + '\'' +
                ", code_ident='" + code_ident + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", id_position=" + id_position +
                '}';
    }
}
