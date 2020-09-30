package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @CacheEvict(value = "shifts", allEntries = true)
    public List<Shift> getAll() {
        return shiftRepository.findAll();
    }

    @CachePut(value = "shifts")
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Cacheable(value = "shifts")
    public Shift findByShiftId(Integer shiftId) {
        Optional<Shift> turno = shiftRepository.findByShiftId(shiftId);

        return turno.orElseThrow(NotFoundException::new);
    }
}
