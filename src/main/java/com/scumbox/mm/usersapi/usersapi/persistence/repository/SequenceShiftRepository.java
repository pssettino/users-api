package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.SequenceShift;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SequenceShiftRepository extends MongoRepository<SequenceShift, String> {
    Optional<SequenceShift> findByIdTurno(Integer idTurno);
}
