package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JustificacionRepository  extends MongoRepository<Justificacion, String> {
    Optional<Justificacion> findByDni(Integer dni);
}
