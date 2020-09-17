package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AbsenceRepository extends MongoRepository<Absence, String> {
    Optional<Absence> findByDocumentNumber(Integer documentNumber);
}
