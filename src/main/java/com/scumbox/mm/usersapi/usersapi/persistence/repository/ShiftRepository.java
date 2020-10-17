package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends MongoRepository<Shift, String> {
    Page<Shift> findAll(Pageable pageable);
    Page<Shift> findById(String id, Pageable pageable);
    Page<Shift> findByDescriptionContaining(String description, Pageable pageable);
    List<Shift> findByDescriptionContaining(String description, Sort sort);
}
