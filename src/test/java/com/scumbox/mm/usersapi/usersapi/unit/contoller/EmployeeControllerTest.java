package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.EmployeeController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeControllerTest {
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    ExtraHoursService extraHoursService = Mockito.mock(ExtraHoursService.class);
    EmployeeController employeeController = new EmployeeController(employeeService, extraHoursService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setStatus(true);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee.get());
        Page<Employee> page = new PageImpl<Employee>(employees);
        Mockito.when(employeeService.getAll(null, null, null)).thenReturn(page);

        // WHEN
        ResponseEntity<Map<String, Object>> result = employeeController.getList(null, null, null);

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void test_findById_when_has_value() {
        // GIVEN
        Employee emp = new Employee();
        emp.setId("33633264");
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        Mockito.when(employeeService.findById(Mockito.anyString())).thenReturn(employee.get());

        // WHEN
        ResponseEntity<Map<String, Object>> result = employeeController.getOne("33633264");

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Employee emp = new Employee();
        emp.setDocumentNumber(33633264);
        emp.setFullName("Pablo Settino");
        Optional<Employee> employee = Optional.of(emp);
        Mockito.when(employeeService.save(Mockito.any())).thenReturn(employee.get());
        Mockito.when(extraHoursService.trackExtraHour(Mockito.any(), Mockito.anyBoolean())).thenReturn(new ExtraHours());
        // WHEN
        ResponseEntity<Map<String, Object>> result = employeeController.create(employee.get());

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }
}
