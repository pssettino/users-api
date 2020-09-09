package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

    Optional<Employee> findByDocumentNumber(Integer documentNumber);

    Optional<Employee> findByFullName(String fullName);
}
