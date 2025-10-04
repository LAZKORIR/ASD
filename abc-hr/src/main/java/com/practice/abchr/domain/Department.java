package com.practice.abchr.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Department {
    @EqualsAndHashCode.Include
    @ToString.Include
    private final BigInteger departmentNo;

    @ToString.Include
    private final String name;

    private Employee headOfDepartment;
    private final List<Employee> employees = new ArrayList<>();

    public void setHeadOfDepartment(Employee headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public void addEmployee(Employee e) {
        if (e != null && !employees.contains(e)) employees.add(e);
    }
}
