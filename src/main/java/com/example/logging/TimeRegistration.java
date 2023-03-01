package com.example.logging;

import java.time.LocalDate;

public class TimeRegistration {

    private int id;

    private static int counter=1;
    private Double time;
    private TypeRegTime enumType;
    private String date; //sortByDate String-problem fixa?

    public TimeRegistration(){

    }

    public TimeRegistration(Double time, TypeRegTime enumType, String date) {
        this.time = time;
        this.enumType = enumType;
        this.date = date;
        id=counter;
        counter++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TimeRegistration{" +
                "id=" + id +
                ", time=" + time +
                ", enumType=" + enumType +
                ", date='" + date + '\'' +
                '}';
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public TypeRegTime getEnumType() {
        return enumType;
    }

    public void setEnumType(TypeRegTime enumType) {
        this.enumType = enumType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
