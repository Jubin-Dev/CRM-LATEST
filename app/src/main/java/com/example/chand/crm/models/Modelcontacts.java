package com.example.chand.crm.models;

/**
 * Created by chand on 12/7/2018.
 */

public class Modelcontacts {
    private String name,number;

    public Modelcontacts(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
