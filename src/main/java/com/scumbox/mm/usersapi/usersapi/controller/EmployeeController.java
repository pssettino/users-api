package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private ExtraHoursService extraHoursService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ExtraHoursService extraHoursService) {
        this.employeeService = employeeService;
        this.extraHoursService = extraHoursService;
    }


    @GetMapping("")
    public @ResponseBody
    ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();

        return ResponseEntity.ok(employees);
    }


    @PostMapping("/")
    public @ResponseBody Employee addEmployee(@RequestBody Employee employee) {

        Employee empl = employeeService.save(employee);
        // TODO: SHOULD BE CACHEABLE?
        extraHoursService.trackExtraHour(empl.getDocumentNumber(), empl.getExtraHoursAvailable());
        return empl;

    }

    @GetMapping("/findByFullName")
    public @ResponseBody Employee findByFullName(@RequestParam String fullName) {
        return employeeService.findByFullName(fullName);
    }

    @GetMapping("/{documentNumber}")
    public @ResponseBody Employee findByDocumentNumber(@PathVariable Integer documentNumber) {
        return employeeService.findByDocumentNumber(documentNumber);
    }
}
