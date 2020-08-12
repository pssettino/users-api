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

    @Cacheable("secuencias_turnos")
    public List<SequenceShift> getAll() {
        return secuenciaTurnoRepository.findAll();
    }

    public SequenceShift save(SequenceShift sequenceShift) {
        return secuenciaTurnoRepository.save(sequenceShift);
    }

    @Cacheable("secuencias_turnos")
    public SequenceShift findByIdTurno(Integer idTurno) {
        Optional<SequenceShift> secuenciaTurno = secuenciaTurnoRepository.findByIdTurno(idTurno);

        return secuenciaTurno.orElseThrow(NotFoundException::new);
    }
}
