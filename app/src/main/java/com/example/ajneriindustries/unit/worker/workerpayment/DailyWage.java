package com.example.ajneriindustries.unit.worker.workerpayment;


import com.example.ajneriindustries.unit.worker.Date;

public class DailyWage {
    private Integer wagePaid;
    private Integer totalWage;
    private Date date;

    public DailyWage(Integer wagePaid, Integer totalWage, Date date) {
        this.wagePaid = wagePaid;
        this.totalWage = totalWage;
        this.date = date;
    }

    public DailyWage() {
    }

    public Integer getWagePaid() {
        return wagePaid;
    }

    public void setWagePaid(Integer wagePaid) {
        this.wagePaid = wagePaid;
    }

    public Integer getTotalWage() {
        return totalWage;
    }

    public void setTotalWage(Integer totalWage) {
        this.totalWage = totalWage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
