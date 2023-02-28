package com.example.logging;

import java.util.Date;

public class User {

    //private Long id;
    private String firstName;
    private String lastName;
    private Double time;
    private TypeRegTime enumType;
    private String date;

    public User(){

    }

    public User(String firstName, String lastName, Double time, TypeRegTime enumType, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
        this.enumType = enumType;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
