package com.onaft.kravchenko.wave.waveandroid.model;

public class Experience {
    private int id_experience;
    private String name;

    public Experience(int id_experience, String name) {
        this.id_experience = id_experience;
        this.name = name;
    }

    public int getId_experience() {
        return id_experience;
    }

    public void setId_experience(int id_experience) {
        this.id_experience = id_experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
