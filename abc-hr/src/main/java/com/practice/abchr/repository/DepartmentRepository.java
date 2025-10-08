package com.practice.abchr.repository;

import com.practice.abchr.domain.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {

    // Fetch employees + HOD to avoid N+1
    @EntityGraph(attributePaths = {"employees", "headOfDepartment"})
    List<Department> findAll();
}
