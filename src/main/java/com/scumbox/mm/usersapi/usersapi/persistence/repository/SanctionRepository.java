package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Sanction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SanctionRepository extends MongoRepository<Sanction, String> {
    Optional<Sanction> findByDni(Integer dni);
}

