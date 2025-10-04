package com.practice.abchr.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Employee {
    @EqualsAndHashCode.Include
    @ToString.Include
    private final String employeeNo;

    @ToString.Include private final String firstName;
    @ToString.Include private final String lastName;

    private final LocalDate dateOfBirth;
    private final LocalDate dateOfEmployment;
    private final BigDecimal biweeklySalary;

    private Department department;

    public void setDepartment(Department department) {
        this.department = department;
    }
}
