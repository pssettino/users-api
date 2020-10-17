package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.EmployeeRepository;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

public class EmployeeServiceTest {
    EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeService(employeeRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        List<Employee> employees = new ArrayList<>();
        employees.add(employee.get());
        Page<Employee> page = new PageImpl<Employee>(employees);
        String[] sort = {"documentNumber", "asc"};
        // WHEN
        Mockito.when(employeeRepository.findAll(page.getPageable())).thenReturn(page);
        Page<Employee> result = employeeService.getAll(sort, null, null);

        // THEN
        //Assertions.assertTrue(!result.isEmpty());
    }


    @Test
    public void test_findByFullName_when_has_value() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        List<Employee> employees = new ArrayList<>();
        employees.add(employee.get());
        Page<Employee> page = new PageImpl<Employee>(employees);
        Mockito.when(employeeRepository.findByFullNameContaining("german", page.getPageable())).thenReturn(page);
        Map<String, String> map = new HashMap<>();
        map.put("fullName", "german");
        String[] sort = {"fullName", "asc"};
        // WHEN
        Page<Employee> result = employeeService.getAll(sort, null, map);

        // THEN
        //Assertions.assertTrue(!result.isEmpty());
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee.get());

        // WHEN
        Employee result = employeeService.save(employee.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
