//package com.practice.abchr.repository;
//
//import com.practice.abchr.domain.Department;
//import com.practice.abchr.domain.Employee;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.time.LocalDate;
//import java.util.*;
//
//@Component
//public class DataStore {
//    private final List<Department> departments = new ArrayList<>();
//    private final List<Employee> employees = new ArrayList<>();
//
//    public DataStore() {
//        loadDepartments();
//        loadEmployees();
//        setHeads();
//    }
//
//    private void loadDepartments() {
//        departments.add(new Department(new BigInteger("31288741190182539912"), "Sales"));       // #1
//        departments.add(new Department(new BigInteger("29274582650152771644"), "Marketing"));   // #2
//        departments.add(new Department(new BigInteger("29274599650152771609"), "Engineering")); // #3
//    }
//
//    private void loadEmployees() {
//        addEmployee("000-11-1234", "Michael", "Philips",
//                LocalDate.of(1995,12,31), LocalDate.of(2021,10,15), new BigDecimal("2750.50"), 3);
//        addEmployee("000-61-0987", "Anna", "Smith",
//                LocalDate.of(1981,9,17), LocalDate.of(2022,8,21), new BigDecimal("2500.00"), 2);
//        addEmployee("000-99-1766", "John", "Hammonds",
//                LocalDate.of(2001,7,15), LocalDate.of(2025,1,23), new BigDecimal("1560.75"), 1);
//        addEmployee("000-41-1768", "Barbara", "Jones",
//                LocalDate.of(2000,11,18), LocalDate.of(2025,3,13), new BigDecimal("1650.55"), 2);
//    }
//
//    private void addEmployee(String eno, String first, String last, LocalDate dob, LocalDate doe, BigDecimal biweekly, int deptIndex1Based) {
//        Employee e = new Employee(eno, first, last, dob, doe, biweekly);
//        Department d = departments.get(deptIndex1Based - 1);
//        e.setDepartment(d);
//        d.addEmployee(e);
//        employees.add(e);
//    }
//
//    private void setHeads() {
//        setHeadForDeptIndex(1, "000-61-0987"); // Sales head
//        setHeadForDeptIndex(2, null);          // Marketing head vacancy
//        setHeadForDeptIndex(3, "000-11-1234"); // Engineering head
//    }
//
//    private void setHeadForDeptIndex(int deptIndex1Based, String employeeNoOrNull) {
//        Department dept = departments.get(deptIndex1Based - 1);
//        if (employeeNoOrNull == null) { dept.setHeadOfDepartment(null); return; }
//        employees.stream().filter(e -> e.getEmployeeNo().equals(employeeNoOrNull))
//                .findFirst().ifPresent(dept::setHeadOfDepartment);
//    }
//
//    public List<Department> getDepartments() { return Collections.unmodifiableList(departments); }
//    public List<Employee> getEmployees() { return Collections.unmodifiableList(employees); }
//}
