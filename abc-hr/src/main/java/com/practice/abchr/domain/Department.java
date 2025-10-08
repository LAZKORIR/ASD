package com.practice.abchr.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {

    @Id
    @Column(name = "department_no", precision = 38, scale = 0, nullable = false)
    @EqualsAndHashCode.Include
    private BigInteger departmentNo;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Optional HOD; may reference an employee from any department (matches quiz data)
    @OneToOne
    @JoinColumn(name = "head_employee_no", referencedColumnName = "employee_no")
    private Employee headOfDepartment;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
}
