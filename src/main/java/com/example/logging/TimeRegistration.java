package com.example.logging;

import java.time.LocalDate;

public class TimeRegistration {

    private Double time;
    private TypeRegTime enumType;
    private LocalDate date;

    public TimeRegistration(){

    }

    public TimeRegistration(Double time, TypeRegTime enumType, LocalDate date) {
        this.time = time;
        this.enumType = enumType;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
