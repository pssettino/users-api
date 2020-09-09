package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Sanction;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {
    @Autowired
    private SanctionRepository sanctionRepository;

    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
    }

    public List<Sanction> getAll() {
        return sanctionRepository.findAll();
    }

    public Sanction save(Sanction sanction) {
        return sanctionRepository.save(sanction);
    }

    public Sanction findByDni(Integer dni) {
        Optional<Sanction> sanction = sanctionRepository.findByDni(dni);

        return sanction.orElseThrow(NotFoundException::new);
    }
}
