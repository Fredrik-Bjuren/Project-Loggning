package com.example.logging;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min=1,max=20, message="Must be between 1-20 characters.")
    @NotEmpty(message=("First name is mandatory."))
    private String firstName;
    @Size(min=1,max=20, message="Must be between 1-20 characters.")
    @NotEmpty(message=("Last name is mandatory."))
    private String lastName;
    @Size(min=3,max=15,message="Must be between 3-15 characters.")
    @NotEmpty(message=("Username is mandatory."))
    // @Column(nullable = false) <--- look at later (input control)?
    private String username;
    @Email(message="Invalid email.")
    @NotEmpty(message=("Email is mandatory."))
    private String email;

    @Size(min=6,max=20,message="Password must be between 6-20 characters")
    private String password;
    
    @Transient
    private String repeatPassword;

    public User() {
    }


    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastname;
        //userTimeRegistrations = createTestArray();
    }
    public User(String username, String email, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        //userTimeRegistrations = createTestArray();

    }

    public User(String firstName, String lastName, String username, String email, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
