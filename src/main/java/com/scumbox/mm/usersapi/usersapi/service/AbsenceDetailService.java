package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceDetailService {

    @Autowired
    private AbsenceDetailRepository absenceDetailRepository;

    public AbsenceDetailService(AbsenceDetailRepository absenceDetailRepository) {
        this.absenceDetailRepository = absenceDetailRepository;
    }

    // @CacheEvict(value = "absences", allEntries = true)
    public List<AbsenceDetail> getAll() {
        return absenceDetailRepository.findAll();
    }

    // @CachePut(value = "absences")
    public AbsenceDetail save(AbsenceDetail absence) {
        return absenceDetailRepository.save(absence);
    }

    // @Cacheable(value = "absences")
    public List<AbsenceDetail> findByAbsenceId(String absenceId) {
        Optional<List<AbsenceDetail>> absence = absenceDetailRepository.findByAbsenceId(absenceId);

        return absence.orElse(null);
    }

    // @Cacheable(value = "absences")
    public AbsenceDetail findById(String id) {
        Optional<AbsenceDetail> absence = absenceDetailRepository.findById(id);

        return absence.orElseThrow(NotFoundException::new);
    }
}

