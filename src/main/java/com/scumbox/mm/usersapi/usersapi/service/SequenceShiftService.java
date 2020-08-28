package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SequenceShift;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.SequenceShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SequenceShiftService {

    @Autowired
    private SequenceShiftRepository secuenciaTurnoRepository;

    public SequenceShiftService(SequenceShiftRepository secuenciaTurnoRepository) {
        this.secuenciaTurnoRepository = secuenciaTurnoRepository;
    }

    public List<SequenceShift> getAll() {
        return secuenciaTurnoRepository.findAll();
    }

    public SequenceShift save(SequenceShift sequenceShift) {
        return secuenciaTurnoRepository.save(sequenceShift);
    }

    public SequenceShift findByShiftId(Integer shiftId) {
        Optional<SequenceShift> secuenciaTurno = secuenciaTurnoRepository.findByShiftId(shiftId);

        return secuenciaTurno.orElseThrow(NotFoundException::new);
    }
}
