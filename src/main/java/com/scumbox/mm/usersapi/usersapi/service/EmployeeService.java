package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    @CacheEvict(value = "employees", allEntries = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @CachePut(value = "employees")
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Cacheable(value = "employees")
    public Employee findByFullName(String fullName) {
        Optional<Employee> employee = employeeRepository.findByFullName(fullName);

        return employee.orElseThrow(NotFoundException::new);
    }

    @Cacheable(value = "employees")
    public Employee findByDocumentNumber(Integer documentNumber) {
        Optional<Employee> employee = employeeRepository.findByDocumentNumber(documentNumber);

        return employee.orElseThrow(NotFoundException::new);
    }
}

