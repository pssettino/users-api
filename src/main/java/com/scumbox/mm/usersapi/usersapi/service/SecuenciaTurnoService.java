package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SecuenciaTurno;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.SecuenciaTurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecuenciaTurnoService {

    @Autowired
    private SecuenciaTurnoRepository secuenciaTurnoRepository;

    public SecuenciaTurnoService(SecuenciaTurnoRepository secuenciaTurnoRepository) {
        this.secuenciaTurnoRepository = secuenciaTurnoRepository;
    }

    @Cacheable("secuencias_turnos")
    public List<SecuenciaTurno> getAll() {
        return secuenciaTurnoRepository.findAll();
    }

    public SecuenciaTurno save(SecuenciaTurno secuenciaTurno) {
        return secuenciaTurnoRepository.save(secuenciaTurno);
    }

    @Cacheable("secuencias_turnos")
    public SecuenciaTurno findByIdTurno(Integer idTurno) {
        Optional<SecuenciaTurno> secuenciaTurno = secuenciaTurnoRepository.findByIdTurno(idTurno);

        return secuenciaTurno.orElseThrow(NotFoundException::new);
    }
}
