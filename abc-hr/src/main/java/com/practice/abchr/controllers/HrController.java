
package com.practice.abchr.controllers;

import com.practice.abchr.dto.DepartmentView;
import com.practice.abchr.dto.EmployeeView;
import com.practice.abchr.service.HrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HrController {
    private final HrService service;

    @GetMapping("/departments")
    public List<DepartmentView> listDepartments() { return service.getDepartmentsView(); }

    @GetMapping("/employees")
    public List<EmployeeView> listEmployees() { return service.getEmployeesView(); }
}
