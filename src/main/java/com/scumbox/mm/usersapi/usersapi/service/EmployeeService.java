package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable("employees")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Cacheable("employees")
    public Employee findByFullName(String fullName) {
        Optional<Employee> employee = employeeRepository.findByFullName(fullName);

        return employee.orElseThrow(NotFoundException::new);
    }

    @Cacheable("employees")
    public Employee findByDni(Integer dni) {
        Optional<Employee> employee = employeeRepository.findByDni(dni);

        return employee.orElseThrow(NotFoundException::new);
    }
}
