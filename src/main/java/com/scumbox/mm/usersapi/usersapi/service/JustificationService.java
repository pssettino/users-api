package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.JustificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificationService {
    @Autowired
    private JustificationRepository justificationRepository;

    public JustificationService(JustificationRepository justificationRepository) {
        this.justificationRepository = justificationRepository;
    }

    public List<Justification> getAll() {
        return justificationRepository.findAll();
    }

    public Justification save(Justification justification) {
        return justificationRepository.save(justification);
    }

    public Justification findByDni(Integer dni) {
        Optional<Justification> justificacion = justificationRepository.findByDni(dni);

        return justificacion.orElseThrow(NotFoundException::new);
    }
}
