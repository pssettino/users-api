package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Fichaje;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.FichajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichajeService {
    @Autowired
    private FichajeRepository fichajeRepository;

    public FichajeService(FichajeRepository fichajeRepository) {
        this.fichajeRepository = fichajeRepository;
    }

    @Cacheable("fichajes")
    public List<Fichaje> getAll() {
        return fichajeRepository.findAll();
    }

    public Fichaje save(Fichaje fichaje) {
        return fichajeRepository.save(fichaje);
    }

    @Cacheable("fichajes")
    public Fichaje findByDni(Integer dni) {
        Optional<Fichaje> fichaje = fichajeRepository.findByDni(dni);

        return fichaje.orElseThrow(NotFoundException::new);
    }
}
