package com.onaft.kravchenko.wave.waveandroid.model;

public class TypeShooting {
    private int id_type_shooting;
    private String name;

    public TypeShooting(int id_type_shooting, String name) {
        this.id_type_shooting = id_type_shooting;
        this.name = name;
    }

    public int getId_type_shooting() {
        return id_type_shooting;
    }

    public void setId_type_shooting(int id_type_shooting) {
        this.id_type_shooting = id_type_shooting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
