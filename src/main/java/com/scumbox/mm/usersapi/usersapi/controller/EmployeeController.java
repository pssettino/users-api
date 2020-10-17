package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "fullName, asc", required = false) String[] sort,
            @RequestParam(required = false) Integer[] range,
            @RequestParam(required = false) Map<String, String> filter
    ) {

        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();

            Page<Employee> employees = employeeService.getAll(sort, range, filter);

            response.put("data", employees.getContent());
            response.put("total", employees.getTotalElements());
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOne(@PathVariable String id){
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            response.put("data", employeeService.findById(id));
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Employee employee) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            Employee empl = employeeService.save(employee);
            // TODO: SHOULD BE CACHEABLE?
            extraHoursService.trackExtraHour(empl.getId(), empl.getExtraHoursAvailable());


            response.put("data", empl);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @RequestBody Employee employee) {
        Map<String, Object> response;
        try {
            //Employee empl = employeeService.findById(id);
            Employee previousData = employee;
            employee.setId(id);
            employee = employeeService.save(employee);
            // TODO: SHOULD BE CACHEABLE?
            extraHoursService.trackExtraHour(employee.getId(), employee.getExtraHoursAvailable());
            response = new HashMap<>();
            response.put("id", id);
            response.put("data", employee);
            response.put("previousData", previousData);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
        Map<String, Object> response;
        try {
            Employee empl = employeeService.findById(id);
            Employee previousData = empl;
            empl.setStatus(false);
            employeeService.save(empl);
            response = new HashMap<>();
            response.put("id", id);
            response.put("previousData", previousData);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
