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
    @SerializedName("date_start")
    @Expose
    private Date date_start;
    @SerializedName("date_end")
    @Expose
    private Date date_end;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("id_shooting")
    @Expose
    private int id_shooting;

    public Event() {
    }

    public Event(int id_event, String name, String description, Date date_start, Date date_end, String address, int id_shooting) {
        this.id_event = id_event;
        this.name = name;
        this.description = description;
        this.date_start = date_start;
        this.date_end = date_end;
        this.address = address;
        this.id_shooting = id_shooting;
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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }
}
