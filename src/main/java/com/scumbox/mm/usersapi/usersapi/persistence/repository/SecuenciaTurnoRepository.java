package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.SecuenciaTurno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SecuenciaTurnoRepository  extends MongoRepository<SecuenciaTurno, String> {
    Optional<SecuenciaTurno> findByIdTurno(Integer idTurno);
}
