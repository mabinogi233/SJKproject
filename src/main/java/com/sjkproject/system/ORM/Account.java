package com.sjkproject.system.ORM;

import org.springframework.stereotype.Component;

@Component
public class Account {

    private int Yid;

    private String UserId;

    private String Password;

    private String Jurisdiction;

    public String getUserId() {
        return UserId;
    }

    public String getPassword() {
        return Password;
    }

    public int getYid() {
        return Yid;
    }

    public String getJurisdiction() {
        return Jurisdiction;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setJurisdiction(String jurisdiction) {
        Jurisdiction = jurisdiction;
    }

    public void setYid(int yid) {
        Yid = yid;
    }
}
