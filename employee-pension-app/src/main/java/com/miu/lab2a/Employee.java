package com.miu.lab2a;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private PensionPlan pensionPlan;

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    public boolean isEnrolled() {
        return pensionPlan != null;
    }

    public int getYearsEmployed() {
        return Period.between(employmentDate, LocalDate.now()).getYears();
    }


}
