package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraHoursRepository extends JpaRepository<ExtraHours, Integer> {
    Page<ExtraHours> findAll(Pageable pageable);
    Page<ExtraHours> findById(String id, Pageable pageable);
    Page<ExtraHours> findByDocumentNumber(Integer documentNumber, Pageable pageable);
    List<ExtraHours> findByDocumentNumber(Integer documentNumber, Sort sort);
    Page<ExtraHours> findByEmployeeId(String employeeId, Pageable pageable);
    List<ExtraHours> findByEmployeeId(String employeeId, Sort sort);
}
