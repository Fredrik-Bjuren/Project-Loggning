package com.example.logging;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TimeRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int userId;
    private LocalDate date;
    private double time;
    private String typeOfTime;
    private String comment;

    private LocalDate created;
    private LocalDate updated;

    public TimeRegistration() {
    }

    public TimeRegistration(Integer id, int userId, LocalDate date, double time, String typeOfTime, String comment, LocalDate created, LocalDate updated) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.typeOfTime = typeOfTime;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getTypeOfTime() {
        return typeOfTime;
    }

    public void setTypeOfTime(String typeOfTime) {
        this.typeOfTime = typeOfTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }
}
