package com.example.chand.crm.models;

/**
 * Created by chand on 12/6/2018.
 */

public class ModelCalls  {
    private  String name,number,duration,date,call_type;
    public  ModelCalls(String name,String number,String duration,String date,String call_type){
        this.name = name;
        this.number = number;
        this.duration = duration;
        this.date = date;
        this.call_type = call_type;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getType() {
        return call_type;
    }

    public void setType(String type) {
        this.call_type = type;
    }
}
