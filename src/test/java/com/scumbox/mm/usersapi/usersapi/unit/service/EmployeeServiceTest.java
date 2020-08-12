package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.EmployeeRepository;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceTest {
    EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeService(employeeRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        List<Employee> employees = new ArrayList<>();
        employees.add(employee.get());

        // WHEN
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByFullName_when_has_value() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        Mockito.when(employeeRepository.findByFullName(Mockito.anyString())).thenReturn(employee);

        // WHEN
        Employee result = employeeService.findByFullName("gernan");

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByFullName_when_hasNot_value() {
        // GIVEN
        Mockito.when(employeeRepository.findByFullName(Mockito.anyString())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Employee result = employeeService.findByFullName("gernan");

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Employee> employee = Optional.of(new Employee());
        Mockito.when(employeeRepository.findByDni(Mockito.anyInt())).thenReturn(employee);

        // WHEN
        Employee result = employeeService.findByDni(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(employeeRepository.findByDni(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Employee result = employeeService.findByDni(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
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
