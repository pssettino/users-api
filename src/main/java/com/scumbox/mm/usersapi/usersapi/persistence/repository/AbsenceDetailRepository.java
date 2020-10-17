package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AbsenceDetailRepository extends MongoRepository<AbsenceDetail, String> {
    Page<AbsenceDetail> findAll(Pageable pageable);
    Page<AbsenceDetail> findByDescriptionContaining(String descripion, Pageable pageable);
    List<AbsenceDetail> findByDescriptionContaining(String descripion, Sort sort);
    Page<AbsenceDetail> findByEmployeeId(String employeeId, Pageable pageable);
    List<AbsenceDetail> findByEmployeeId(String employeeId, Sort sort);
    Page<AbsenceDetail> findById(String id, Pageable pageable);
    List<AbsenceDetail> findById(String id, Sort sort);
}
