package com.onaft.kravchenko.wave.waveandroid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Shooting {

    @SerializedName("id_shooting")
    @Expose
    private int id_shooting;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("typeShooting")
    @Expose
    private TypeShooting typeShooting;

    @SerializedName("date_start")
    @Expose
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private String date_start;
    @SerializedName("date_end")
    @Expose
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private String date_end;

    public Shooting() {
    }

    public Shooting(int id_shooting, String purpose, TypeShooting typeShooting, String date_start, String date_end) {
        this.id_shooting = id_shooting;
        this.purpose = purpose;
        this.typeShooting = typeShooting;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public TypeShooting getTypeShooting() {
        return typeShooting;
    }

    public void setTypeShooting(TypeShooting typeShooting) {
        this.typeShooting = typeShooting;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id_shooting\":" + id_shooting +
                ", \"purpose\":\"" + purpose + '\"' +
                ", \"typeShooting\":" + typeShooting.toString() +
                ", \"date_start\":\"" + date_start + '\"' +
                ", \"date_end\":\"" + date_end + '\"' +
                '}';
    }
}
