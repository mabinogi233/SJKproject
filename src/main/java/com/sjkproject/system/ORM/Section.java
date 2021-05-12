package com.sjkproject.system.ORM;

public class Section {

    private int Bid;

    private String BName;

    private String Location;

    public int getBid() {
        return Bid;
    }

    public String getBName() {
        return BName;
    }

    public String getLocation() {
        return Location;
    }

    public void setBid(int bid) {
        Bid = bid;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
