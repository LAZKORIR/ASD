package com.practice.abchr.dto;


import com.practice.abchr.domain.Department;
import com.practice.abchr.dto.EmployeeView;
import lombok.Builder;
import lombok.Value;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentView {
    @JsonProperty("departmentNo") BigInteger departmentNo;
    @JsonProperty("name") String name;
    @JsonProperty("headOfDepartment")
    EmployeeView headOfDepartment;
    @JsonProperty("totalAnnualSalary") BigDecimal totalAnnualSalary;
    @JsonProperty("employees") List<EmployeeView> employees;

    public static DepartmentView from(Department d, BigDecimal totalAnnualSalary) {
        return DepartmentView.builder()
                .departmentNo(d.getDepartmentNo())
                .name(d.getName())
                .totalAnnualSalary(totalAnnualSalary)
                .headOfDepartment(d.getHeadOfDepartment() == null ? null : EmployeeView.from(d.getHeadOfDepartment(), null))
                .employees(d.getEmployees().stream().map(e -> EmployeeView.from(e, null)).collect(Collectors.toList()))
                .build();
    }
}
