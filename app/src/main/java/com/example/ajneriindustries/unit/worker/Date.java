package com.example.ajneriindustries.unit.worker;

import androidx.annotation.Nullable;

import java.text.DateFormatSymbols;

public class Date {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(){

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Date newDate=(Date)obj;
        return newDate.day == day && newDate.month == month && newDate.year == year;
    }

    @Override
    public String toString() {
        String monthString = new DateFormatSymbols().getMonths()[month-1];
        return day+" "+monthString+" "+year;
    }
    public boolean lessThenEqualTo(Date referenceDay)
    {
        if(year>referenceDay.year) return false;
        else
        {
            if(month>referenceDay.month) return false;
            else
            {
                if(day>referenceDay.day) return false;
                else return true;
            }
        }
    }
    public boolean greaterThenEqualTo(Date referenceDay)
    {
        if(year<referenceDay.year) return false;
        else
        {
            if(month<referenceDay.month) return false;
            else
            {
                if(day<referenceDay.month) return false;
                else return true;
            }
        }
    }
}
