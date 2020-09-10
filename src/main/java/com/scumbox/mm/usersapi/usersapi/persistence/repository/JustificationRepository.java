package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JustificationRepository extends MongoRepository<Justification, String> {
    Optional<Justification> findByDocumentNumber(Integer documentNumber);
}
