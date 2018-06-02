package com.onaft.kravchenko.wave.waveandroid.model;

import java.util.Date;

public class Event {
    private int id_event;
    private String name;
    private String description;
    private Date date_start;
    private Date date_end;

    public Event(int id_event, String name, String description, Date date_start, Date date_end) {
        this.id_event = id_event;
        this.name = name;
        this.description = description;
        this.date_start = date_start;
        this.date_end = date_end;
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
}
