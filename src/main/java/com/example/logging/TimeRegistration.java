package com.example.logging;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String typeOfWork;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TimeRegistration() {
    }

    public TimeRegistration(Integer id, Integer user_id, LocalDate date, Double time, String typeOfWork, String comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.typeOfWork = typeOfWork;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TimeRegistration(Integer user_id, LocalDate date, Double time, String typeOfWork, String comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.typeOfWork = typeOfWork;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
