package com.onaft.kravchenko.wave.waveandroid.util;

import java.io.Serializable;

public class WorkRating{
    private int id_employee;
    private int work_interest;

    private WorkRating(){}
    public WorkRating(int id_employee, int work_interest) {
        this.id_employee = id_employee;
        this.work_interest = work_interest;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getWork_interest() {
        return work_interest;
    }

    public void setWork_interest(int work_interest) {
        this.work_interest = work_interest;
    }
}
