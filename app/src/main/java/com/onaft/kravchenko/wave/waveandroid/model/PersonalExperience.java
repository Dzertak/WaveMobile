package com.onaft.kravchenko.wave.waveandroid.model;

public class PersonalExperience {
    private int id_personal_experience;
    private int id_employee;
    private int id_experience;
    private String comment;

    public PersonalExperience(int id_personal_experience, int id_employee, int id_experience, String comment) {
        this.id_personal_experience = id_personal_experience;
        this.id_employee = id_employee;
        this.id_experience = id_experience;
        this.comment = comment;
    }

    public int getId_personal_experience() {
        return id_personal_experience;
    }

    public void setId_personal_experience(int id_personal_experience) {
        this.id_personal_experience = id_personal_experience;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_experience() {
        return id_experience;
    }

    public void setId_experience(int id_experience) {
        this.id_experience = id_experience;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

