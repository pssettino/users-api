package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.PageHelper;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @CacheEvict(value = "shifts", allEntries = true)
    public Page<Shift> getAll(String[] sort, Integer[] range, Map<String, String> filter) {
        Pageable paging = PageHelper.getPageable(sort, range);

        Page<Shift> pageAbs;
        if (filter == null) {
            pageAbs = shiftRepository.findAll(paging);
        } else {
            if(filter.containsKey("id"))  {
                pageAbs = shiftRepository.findById(filter.get("id"), paging);
            } else if(filter.containsKey("q")) {
                pageAbs = shiftRepository.findByDescriptionContaining(filter.get("q"), paging);
            } else {
                pageAbs = shiftRepository.findAll(paging);
            }
        }

        return pageAbs;
    }

    @CachePut(value = "shifts")
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Cacheable(value = "shifts")
    public Shift findById(String id) {
        Optional<Shift> shift = shiftRepository.findById(id);

        return shift.orElseThrow(NotFoundException::new);
    }

}
