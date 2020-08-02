package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Fichaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FichajeRepository  extends MongoRepository<Fichaje, String> {
    Optional<Fichaje> findByDni(Integer dni);
}
