package com.practice.abchr.repository;


import com.practice.abchr.domain.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @EntityGraph(attributePaths = {"department"})
    List<Employee> findAll();
}
