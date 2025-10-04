package com.practice.abchr.service;


import com.practice.abchr.domain.Employee;
import com.practice.abchr.dto.DepartmentView;
import com.practice.abchr.dto.EmployeeView;
import com.practice.abchr.repository.DataStore;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HrService {
    private final DataStore dataStore;
    public HrService(DataStore dataStore) { this.dataStore = dataStore; }

    public List<DepartmentView> getDepartmentsView() {
        return dataStore.getDepartments().stream()
                .map(d -> {
                    BigDecimal totalAnnual = d.getEmployees().stream()
                            .map(Employee::getBiweeklySalary)
                            .map(bw -> bw.multiply(BigDecimal.valueOf(26)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return DepartmentView.from(d, totalAnnual);
                })
                .sorted(Comparator.comparing(DepartmentView::getTotalAnnualSalary).reversed())
                .collect(Collectors.toList());
    }

    public List<EmployeeView> getEmployeesView() {
        LocalDate today = LocalDate.now();
        return dataStore.getEmployees().stream()
                .map(e -> EmployeeView.from(e, ChronoUnit.YEARS.between(e.getDateOfEmployment(), today)))
                .sorted(Comparator.comparing(EmployeeView::getYearsEmployed).reversed()
                        .thenComparing(EmployeeView::getLastName))
                .collect(Collectors.toList());
    }
}
