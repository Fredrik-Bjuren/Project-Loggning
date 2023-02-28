package com.example.logging;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
@SessionScope
public class User {
    //TODO validation
    private int id;
    int counter=1;
    private String firstName;

    private String lastName;
    private String username;
    private String email;
    private String password;

    private List <TimeRegistration> userTimeRegistrations;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", counter=" + counter +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userTimeRegistrations=" + userTimeRegistrations +
                '}';
    }


    public List<TimeRegistration> getUserTimeRegistrations() {
        return userTimeRegistrations;
    }

    public void sortByDate() {
        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getDate));
        System.out.println("Date");
    }

    public void sortByHours() {
        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getTime));
        System.out.println("Hours");
    }

    public void sortByCategory() {
        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getEnumType));
        System.out.println("Category");
    }
    public void setUserTimeRegistrations(List<TimeRegistration> userTimeRegistrations) {
        this.userTimeRegistrations = userTimeRegistrations;
    }

    public User() {
        id=counter;
        userTimeRegistrations=new ArrayList<>();
        counter++;
    }

    public double getTotalTime(){
        return userTimeRegistrations.stream().mapToDouble(x->x.getTime()).sum();
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

    public void setPassword(String password) {
        this.password = password;
    }
}
