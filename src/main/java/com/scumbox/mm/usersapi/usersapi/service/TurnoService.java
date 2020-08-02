package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Turno;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Cacheable("turnos")
    public List<Turno> getAll() {
        return turnoRepository.findAll();
    }

    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Cacheable("turnos")
    public Turno findByIdTurno(Integer idTurno) {
        Optional<Turno> turno = turnoRepository.findByIdTurno(idTurno);

        return turno.orElseThrow(NotFoundException::new);
    }
}
