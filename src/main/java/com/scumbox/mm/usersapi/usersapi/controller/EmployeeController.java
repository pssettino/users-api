package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }


    @PutMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    @GetMapping("/findByFullName")
    public Employee findByFullName(@RequestParam String fullName) {
        return employeeService.findByFullName(fullName);
    }

    @GetMapping("/dni")
    public Employee findByDni(@RequestParam Integer dni) {
        return employeeService.findByDni(dni);
    }
}
