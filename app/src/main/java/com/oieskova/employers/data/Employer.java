package com.oieskova.employers.data;

import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "employers")
public class Employer {
    @PrimaryKey
    private int idEmployer;
    private String name;
    private String lastName;
    private String birthday;
    private String avatr_url;

    public Employer(int idEmployer, String name, String lastName, String birthday, String avatr_url) {
        this.idEmployer = idEmployer;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatr_url = avatr_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatr_url() {
        return avatr_url;
    }

    public void setAvatr_url(String avatr_url) {
        this.avatr_url = avatr_url;
    }

    public int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    @Override
    public String toString() {
        return name+" "+lastName;
    }
}
