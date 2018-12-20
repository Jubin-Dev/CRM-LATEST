package com.example.chand.crm.models;

public class ModelTasks {
    private String Name;
    private String Adress;
    private  String Email;
    private  String Contacts;

    public ModelTasks() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }

    public ModelTasks(String name, String adress, String email, String contacts) {
        Name = name;
        Adress = adress;
        Email = email;
        Contacts = contacts;

    }



}
