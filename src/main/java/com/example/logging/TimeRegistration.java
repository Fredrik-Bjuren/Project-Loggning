package com.example.logging;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;


@Entity
public class TimeRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private LocalDate date;
    private Double time;
    private String typeOfTime;
    private String comment;
    private LocalDateTime created;
    private LocalDateTime updated;



    public TimeRegistration() {
        time = 0.0;
    }

    public TimeRegistration(Integer id, Integer userId, LocalDate date, double time, String typeOfTime, String comment,
                            LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.typeOfTime = typeOfTime;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }

    public TimeRegistration(Integer userId, LocalDate date, Double time, String typeOfTime, String comment,
                            LocalDateTime created, LocalDateTime updated) {
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.typeOfTime = typeOfTime;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "(" + userId +
                ", '" + date + '\'' +
                ", " + time +
                ", '" + typeOfTime + '\'' +
                ", '" + comment + '\'' +
                ", '" + created + '\'' +
                ", '" + updated + '\'' + ')';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
