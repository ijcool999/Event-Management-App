package com.example.loginregister;

public class User {
    private String date;
    private String event;

    public User(String date, String event) {
        this.date = date;
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }
}