package com.practice.abchr.dto;


import com.practice.abchr.domain.Employee;
import lombok.Builder;
import lombok.Value;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {
    @JsonProperty("employeeNo") String employeeNo;
    @JsonProperty("firstName")  String firstName;
    @JsonProperty("lastName")   String lastName;
    @JsonProperty("dateOfBirth") LocalDate dateOfBirth;
    @JsonProperty("dateOfEmployment") LocalDate dateOfEmployment;
    @JsonProperty("biweeklySalary") BigDecimal biweeklySalary;
    @JsonProperty("department") SimpleDepartment department;
    @JsonProperty("yearsEmployed") Long yearsEmployed;

    public static EmployeeView from(Employee e, Long yearsEmployedOrNull) {
        return EmployeeView.builder()
                .employeeNo(e.getEmployeeNo())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .dateOfBirth(e.getDateOfBirth())
                .dateOfEmployment(e.getDateOfEmployment())
                .biweeklySalary(e.getBiweeklySalary())
                .department(e.getDepartment() == null ? null :
                        new SimpleDepartment(e.getDepartment().getDepartmentNo().toString(), e.getDepartment().getName()))
                .yearsEmployed(yearsEmployedOrNull)
                .build();
    }

    @Value
    public static class SimpleDepartment {
        @JsonProperty("departmentNo") String departmentNo;
        @JsonProperty("name") String name;
    }
}
