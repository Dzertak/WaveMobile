package com.onaft.kravchenko.wave.waveandroid.model;

import java.util.Date;

public class Absence {
    private int id_absence;
    private int id_employee;
    private int id_couse;
    private String comment;
    private Date date_start;
    private Date date_end;

    public Absence(int id_absence, int id_employee, int id_couse, String comment, Date date_start, Date date_end) {
        this.id_absence = id_absence;
        this.id_employee = id_employee;
        this.id_couse = id_couse;
        this.comment = comment;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public int getId_absence() {
        return id_absence;
    }

    public void setId_absence(int id_absence) {
        this.id_absence = id_absence;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_couse() {
        return id_couse;
    }

    public void setId_couse(int id_couse) {
        this.id_couse = id_couse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}
