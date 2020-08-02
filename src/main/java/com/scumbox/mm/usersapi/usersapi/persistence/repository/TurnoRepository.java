package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Turno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TurnoRepository  extends MongoRepository<Turno, String> {
    Optional<Turno> findByIdTurno(Integer idTurno);
}
