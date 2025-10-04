package com.practice.abchr.controllers;


import com.practice.abchr.dto.DepartmentView;
import com.practice.abchr.dto.EmployeeView;
import com.practice.abchr.service.HrService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HrController {
    private final HrService service;
    public HrController(HrService service) { this.service = service; }

    @GetMapping("/departments")
    public List<DepartmentView> listDepartments() {
        return service.getDepartmentsView();
    }

    @GetMapping("/employees")
    public List<EmployeeView> listEmployees() {
        return service.getEmployeesView();
    }
}
