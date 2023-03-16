package com.example.logging;

public enum TypeOfTime {
    WORK("Work"),
    PAID_LEAVE("Paid Leave"),
    UNPAID_LEAVE("Unpaid Leave"),
    SICK_LEAVE("Sick Leave"),
    OVERTIME("Overtime"),
    ;
    private String name;

    TypeOfTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
