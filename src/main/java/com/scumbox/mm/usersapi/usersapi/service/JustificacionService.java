package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justificacion;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.JustificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificacionService {
    @Autowired
    private JustificacionRepository justificacionRepository;

    public JustificacionService(JustificacionRepository justificacionRepository) {
        this.justificacionRepository = justificacionRepository;
    }

    @Cacheable("justificaciones")
    public List<Justificacion> getAll() {
        return justificacionRepository.findAll();
    }

    public Justificacion save(Justificacion justificacion) {
        return justificacionRepository.save(justificacion);
    }

    @Cacheable("justificaciones")
    public Justificacion findByDni(Integer dni) {
        Optional<Justificacion> justificacion = justificacionRepository.findByDni(dni);

        return justificacion.orElseThrow(NotFoundException::new);
    }
}
