package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeShooting {
    @SerializedName("id_type_shooting")
    @Expose
    private int id_type_shooting;
    @SerializedName("name")
    @Expose
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
