package com.example.tltdd_bai8.models;

import java.io.Serializable;

public class TinNhan implements Serializable {
    private String number, time, body;

    public TinNhan(String number, String time, String body) {
        this.number = number;
        this.time = time;
        this.body = body;
    }

    public TinNhan() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
