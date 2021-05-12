package com.sjkproject.system.ORM;

import org.springframework.stereotype.Component;

@Component
public class Attendance {
    int Yid;

    String ADate;

    String Information;

    public int getYid() {
        return Yid;
    }

    public String getADate() {
        return ADate;
    }

    public String getInformation() {
        return Information;
    }

    public void setYid(int yid) {
        Yid = yid;
    }

    public void setADate(String ADate) {
        this.ADate = ADate;
    }

    public void setInformation(String information) {
        Information = information;
    }
}
