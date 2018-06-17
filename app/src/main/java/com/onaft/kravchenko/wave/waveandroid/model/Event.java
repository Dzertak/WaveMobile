package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {
    @SerializedName("id_event")
    @Expose
    private int id_event;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("shooting")
    @Expose
    private Shooting shooting;
    @SerializedName("id_customer")
    @Expose
    private int id_customer;

    public Event() {
    }

    public Event(int id_event, String name, String description, String address, Shooting shooting, int id_customer) {
        this.id_event = id_event;
        this.name = name;
        this.description = description;
        this.address = address;
        this.shooting = shooting;
        this.id_customer = id_customer;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Shooting getShooting() {
        return shooting;
    }

    public void setShooting(Shooting shooting) {
        this.shooting = shooting;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }
}
