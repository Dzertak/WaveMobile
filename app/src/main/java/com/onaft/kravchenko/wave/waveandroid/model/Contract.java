package com.onaft.kravchenko.wave.waveandroid.model;

public class Contract {
    private int id_contract;
    private int id_customer;
    private int id_event;
    private int id_shooting;
    private String description;

    public Contract(int id_contract, int id_customer, int id_event, int id_shooting, String description) {
        this.id_contract = id_contract;
        this.id_customer = id_customer;
        this.id_event = id_event;
        this.id_shooting = id_shooting;
        this.description = description;
    }

    public int getId_contract() {
        return id_contract;
    }

    public void setId_contract(int id_contract) {
        this.id_contract = id_contract;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
