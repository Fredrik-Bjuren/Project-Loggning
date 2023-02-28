package com.example.logging;

public class User {

    //private Long id;
    private String firstName;
    private String lastName;
    private Double time;

    private TypeRegTime enumType;

    public User(String firstName, String lastName, Double time, TypeRegTime enumType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
        this.enumType = enumType;
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
}
