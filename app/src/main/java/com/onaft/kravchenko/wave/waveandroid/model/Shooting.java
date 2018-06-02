package com.onaft.kravchenko.wave.waveandroid.model;

public class Shooting {
    private int id_shooting;
    private int id_type_shooting;
    private String purpose;

    public Shooting(int id_shooting, int id_type_shooting, String purpose) {
        this.id_shooting = id_shooting;
        this.id_type_shooting = id_type_shooting;
        this.purpose = purpose;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

    public int getId_type_shooting() {
        return id_type_shooting;
    }

    public void setId_type_shooting(int id_type_shooting) {
        this.id_type_shooting = id_type_shooting;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
