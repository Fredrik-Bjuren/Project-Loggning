package com.example.logging;

public class User {

    //private Long id;
    private String firstName;
    private String lastName;
    private Double time;

    public User(String firstName, String lastName, Double time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
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
}
