package com.onaft.kravchenko.wave.waveandroid.model;

public class ShootingGroup {
    private int id_shooting_group;
    private int id_employee;
    private int id_shooting;

    public ShootingGroup(int id_shooting_group, int id_employee, int id_shooting) {
        this.id_shooting_group = id_shooting_group;
        this.id_employee = id_employee;
        this.id_shooting = id_shooting;
    }

    public int getId_shooting_group() {
        return id_shooting_group;
    }

    public void setId_shooting_group(int id_shooting_group) {
        this.id_shooting_group = id_shooting_group;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }
}
