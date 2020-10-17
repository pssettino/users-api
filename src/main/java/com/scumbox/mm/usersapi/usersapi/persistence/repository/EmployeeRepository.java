package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findById(String id, Pageable pageable);
    Page<Employee> findByDocumentNumber(Integer documentNumber, Pageable pageable);
    List<Employee> findByDocumentNumber(Integer documentNumber, Sort sort);
    Page<Employee> findByFullNameContaining(String fullName, Pageable pageable);
    List<Employee> findByFullNameContaining(String fullName, Sort sort);
}
