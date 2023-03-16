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
    private Integer user_id;
    private LocalDate date;
    private Double time;
    private String typeOfTime;
    private String comment;
    private LocalDateTime created;
    private LocalDateTime updated;


    public TimeRegistration() {
    }

    public TimeRegistration(Integer id, Integer userId, LocalDate date, double time, String typeOfTime, String comment, LocalDate created, LocalDate updated) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.typeOfTime = typeOfTime;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }

    public TimeRegistration(Integer user_id, LocalDate date, Double time, String typeOfTime, String comment, LocalDateTime created, LocalDateTime updated) {
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.typeOfTime = typeOfTime;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }
    
     @Override
    public String toString() {
        return "(" + user_id +
                ", '" + date + '\'' +
                ", " + time +
                ", '" + typeOfWork + '\'' +
                ", '" + comment + '\'' +
                ", '" + createdAt + '\'' +
                ", '" + updatedAt + '\'' + ')';
    
    public Integer getId() {
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
  


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
