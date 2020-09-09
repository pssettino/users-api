package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.EmployeeController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeControllerTest {
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    EmployeeController employeeController = new EmployeeController(employeeService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee.get());
        Mockito.when(employeeService.getAll()).thenReturn(employees);

        // WHEN
        List<Employee> result = employeeController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        Mockito.when(employeeService.findByDocumentNumber(Mockito.anyInt())).thenReturn(employee.get());

        // WHEN
        Employee result = employeeController.findByDocumentNumber(33633264);

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }

    @Test
    public void test_findByFullName_when_has_value() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        Mockito.when(employeeService.findByFullName(Mockito.anyString())).thenReturn(employee.get());

        // WHEN
        Employee result = employeeController.findByFullName("Pablo Settino");

        // THEN
        Assertions.assertTrue(result.getFullName().equals("Pablo Settino"));
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(employeeService.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Employee result = employeeController.findByDocumentNumber(33633264);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_findByFulllName_when_hasNot_value() {
        // GIVEN
        Mockito.when(employeeService.findByFullName(Mockito.anyString())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Employee result = employeeController.findByFullName("Pablo Settino");

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        Mockito.when(employeeService.save(Mockito.any())).thenReturn(employee.get());

        // WHEN
        Employee result = employeeController.addEmployee(employee.get());

        // THEN
        Assertions.assertTrue(result.getFullName().equals("Pablo Settino"));
    }
}
