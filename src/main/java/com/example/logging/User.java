package com.example.logging;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class User {
    private int id;
    private static int counter=1;
    @Size(min=1,max=20, message="Must be between 1-20 characters.")
    @NotEmpty(message=("First name is mandatory."))
    private String firstName;
    @Size(min=1,max=20, message="Must be between 1-20 characters.")
   @NotEmpty(message=("Last name is mandatory."))
    private String lastName;
    @Size(min=3,max=15,message="Must be between 3-15 characters.")
   @NotEmpty(message=("Username is mandatory."))
    private String username;
    @Email
   @NotEmpty(message=("Email is mandatory."))
    private String email;
    @Size(min=6,max=20,message="Password must be between 6-20 characters")
    private String password;
    private List <TimeRegistration> userTimeRegistrations;


    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastname;
        userTimeRegistrations = createTestArray();
        id=counter;
        counter++;
    }

    public User(String username, String email, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        userTimeRegistrations = createTestArray();
        id=counter;
        counter++;
    }

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


    public User() {
        id=counter;
        userTimeRegistrations=createTestArray();
        counter++;
    }

    public List<TimeRegistration> createTestArray() {
        List testArray = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            testArray.add(new TimeRegistration(random.nextDouble(7)+1,
                    TypeRegTime.values()[random.nextInt(5)],
                    LocalDate.now().plusDays(random.nextInt(30)+1).toString(),""));
        }
        return testArray;
    }

    public List<TimeRegistration> getUserTimeRegistrations() {
        return userTimeRegistrations;
    }

    public double getEnumSum(TypeRegTime type){
        return userTimeRegistrations.stream().filter(z->z.getEnumType()==type).mapToDouble(z->z.getTime()).sum();
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
