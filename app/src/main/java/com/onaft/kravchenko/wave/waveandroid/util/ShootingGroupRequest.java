package com.onaft.kravchenko.wave.waveandroid.util;

import com.onaft.kravchenko.wave.waveandroid.model.Employee;

import java.util.List;

public class ShootingGroupRequest {
    private int id_shooting;
    private List<Employee> employees;

    public ShootingGroupRequest(){}
    public ShootingGroupRequest(int id_shooting, List<Employee> employees) {
        this.id_shooting = id_shooting;
        this.employees = employees;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
