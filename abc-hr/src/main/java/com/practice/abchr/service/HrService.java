package com.practice.abchr.service;


import com.practice.abchr.domain.Employee;
import com.practice.abchr.dto.DepartmentView;
import com.practice.abchr.dto.EmployeeView;
import com.practice.abchr.repository.DepartmentRepository;
import com.practice.abchr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HrService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<DepartmentView> getDepartmentsView() {
        return departmentRepository.findAll().stream()
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
        return employeeRepository.findAll().stream()
                .map(e -> EmployeeView.from(e, ChronoUnit.YEARS.between(e.getDateOfEmployment(), today)))
                .sorted(Comparator.comparing(EmployeeView::getYearsEmployed).reversed()
                        .thenComparing(EmployeeView::getLastName))
                .collect(Collectors.toList());
    }
}
