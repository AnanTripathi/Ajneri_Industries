package com.example.ajneriindustries.unit.worker;

import java.util.Date;

public class DailyWage {
    private Integer wageToBePaid;
    private Integer wageAlreadyPaid;
    private Date date;

    public DailyWage(Integer wageToBePaid, Integer wageAlreadyPaid, Date date) {
        this.wageToBePaid = wageToBePaid;
        this.wageAlreadyPaid = wageAlreadyPaid;
        this.date = date;
    }

    public DailyWage() {
    }

    public Integer getWageToBePaid() {
        return wageToBePaid;
    }

    public void setWageToBePaid(Integer wageToBePaid) {
        this.wageToBePaid = wageToBePaid;
    }

    public Integer getWageAlreadyPaid() {
        return wageAlreadyPaid;
    }

    public void setWageAlreadyPaid(Integer wageAlreadyPaid) {
        this.wageAlreadyPaid = wageAlreadyPaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
