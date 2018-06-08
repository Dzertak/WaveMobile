package com.onaft.kravchenko.wave.waveandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public Shooting() {
    }

    public Shooting(int id_shooting, String purpose, TypeShooting typeShooting) {
        this.id_shooting = id_shooting;
        this.purpose = purpose;
        this.typeShooting = typeShooting;
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

    public class Type{
        @SerializedName("id_type_shooting")
        @Expose
        private int id_type_shooting;
        @SerializedName("name")
        @Expose
        private String name;

        public Type(int id_type_shooting, String name) {
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
}
