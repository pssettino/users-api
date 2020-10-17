package com.scumbox.mm.usersapi.usersapi.service;

import com.google.common.base.Strings;
import com.scumbox.mm.usersapi.usersapi.PageHelper;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AbsenceDetailService {

    @Autowired
    private AbsenceDetailRepository absenceDetailRepository;

    public AbsenceDetailService(AbsenceDetailRepository absenceDetailRepository) {
        this.absenceDetailRepository = absenceDetailRepository;
    }

    // @CacheEvict(value = "absences", allEntries = true)
    public Page<AbsenceDetail> getAll(String[] sort, Integer[] range, Map<String, String> filter) {
        Pageable paging = PageHelper.getPageable(sort, range);

        Page<AbsenceDetail> pageAbs;
        if (filter == null) {
            pageAbs = absenceDetailRepository.findAll(paging);
        } else {
            if(filter.containsKey("id"))  {
                pageAbs = absenceDetailRepository.findById(filter.get("id"), paging);
            } else if(filter.containsKey("employeeId")) {
                pageAbs = absenceDetailRepository.findByEmployeeId(filter.get("employeeId"), paging);
            } else if(filter.containsKey("q")) {
                pageAbs = absenceDetailRepository.findByDescriptionContaining(filter.get("q"), paging);
            } else {
                pageAbs = absenceDetailRepository.findAll(paging);
            }
        }

        return pageAbs;
    }

    // @CachePut(value = "absences")
    public AbsenceDetail save(AbsenceDetail absence) {
        return absenceDetailRepository.save(absence);
    }

    // @Cacheable(value = "absences")
    public AbsenceDetail findById(String id) {
        Optional<AbsenceDetail> absence = absenceDetailRepository.findById(id);

        return absence.orElseThrow(NotFoundException::new);
    }
}

