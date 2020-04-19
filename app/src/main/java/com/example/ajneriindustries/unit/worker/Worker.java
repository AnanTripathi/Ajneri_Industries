package com.example.ajneriindustries.unit.worker;

import java.util.ArrayList;
import java.util.Date;

public class Worker {
    private String name;
    private Date joiningDate;
    private Date leavingDate;
    private ArrayList<DailyWage> wagesList;
    private String workerId;

    public Worker(String name, Date joiningDate, Date leavingDate, ArrayList<DailyWage> wagesList,String workerId) {
        this.name = name;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
        this.wagesList = wagesList;
        this.workerId=workerId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public ArrayList<DailyWage> getWagesList() {
        return wagesList;
    }

    public void setWagesList(ArrayList<DailyWage> wagesList) {
        this.wagesList = wagesList;
    }

    public Worker(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }
}
