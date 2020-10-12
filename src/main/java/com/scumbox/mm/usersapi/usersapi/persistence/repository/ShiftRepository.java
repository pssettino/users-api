package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShiftRepository extends MongoRepository<Shift, String> {
    Optional<Shift> findByDescription(String description);
}
