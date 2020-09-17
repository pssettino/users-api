package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Absence> getAll() {
        return absenceRepository.findAll();
    }

    public Absence save(Absence absence) {
        return absenceRepository.save(absence);
    }

    public Absence findByDocumentNumber(Integer documentNumber) {
        Optional<Absence> absence = absenceRepository.findByDocumentNumber(documentNumber);

        return absence.orElseThrow(NotFoundException::new);
    }
}
