package com.example.logging;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class User {

    private int id;
    int counter=1;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    private List <TimeRegistration> userTimeRegistrations;

    public List<TimeRegistration> getUserTimeRegistrations() {
        return userTimeRegistrations;
    }

    public void setUserTimeRegistrations(List<TimeRegistration> userTimeRegistrations) {
        this.userTimeRegistrations = userTimeRegistrations;
    }


    public User(){

    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        id=counter;
        userTimeRegistrations=new ArrayList<>();
        counter++;
    }

    public void addTimeRegistration(TimeRegistration timeRegistration){
        userTimeRegistrations.add(timeRegistration);
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
