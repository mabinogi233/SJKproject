package com.sjkproject.system.ORM;

import org.springframework.stereotype.Component;

@Component
public class Salary {
    private int Yid;

    private String SDate;

    private double BasePay;

    private double reward;

    public int getYid() {
        return Yid;
    }

    public double getBasePay() {
        return BasePay;
    }

    public double getReward() {
        return reward;
    }

    public String getSDate() {
        return SDate;
    }

    public void setYid(int yid) {
        Yid = yid;
    }

    public void setBasePay(double basePay) {
        BasePay = basePay;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public void setSDate(String SDate) {
        this.SDate = SDate;
    }
}
