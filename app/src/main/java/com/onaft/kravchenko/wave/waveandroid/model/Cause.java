package com.onaft.kravchenko.wave.waveandroid.model;

public class Cause {
    private int id_cause;
    private String name;

    public Cause(int id_cause, String name) {
        this.id_cause = id_cause;
        this.name = name;
    }

    public int getId_cause() {
        return id_cause;
    }

    public void setId_cause(int id_cause) {
        this.id_cause = id_cause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
