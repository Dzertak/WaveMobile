package com.onaft.kravchenko.wave.waveandroid.model;

public class Customer {
    private int id_customer;
    private String name;
    private String phone;
    private String address;

    public Customer(){}
    public Customer(int id_customer, String name, String phone, String address) {
        this.id_customer = id_customer;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }
}
