package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    // @CacheEvict(value = "absences", allEntries = true)
    public List<Absence> getAll() {
        return absenceRepository.findAll();
    }

    // @CachePut(value = "absences")
    public Absence save(Absence absence) {
        return absenceRepository.save(absence);
    }

    // @Cacheable(value = "absences")
    public Absence findByDocumentNumber(Integer documentNumber) {
        Optional<Absence> absence = absenceRepository.findByDocumentNumber(documentNumber);

        return absence.orElse(null);
    }

    // @Cacheable(value = "absences")
    public Absence findById(String id) {
        Optional<Absence> absence = absenceRepository.findById(id);

        return absence.orElseThrow(NotFoundException::new);
    }
}
