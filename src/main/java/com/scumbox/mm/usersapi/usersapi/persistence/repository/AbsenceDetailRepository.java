package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AbsenceDetailRepository extends MongoRepository<AbsenceDetail, String> {
    Optional<List<AbsenceDetail>> findByAbsenceId(String absenceId);
}
