package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.PageHelper;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ExtraHoursService {

    private ExtraHoursRepository extraHoursRepository;
    private EmployeeService employeeService;

    @Autowired
    public ExtraHoursService(ExtraHoursRepository extraHoursRepository, EmployeeService employeeService) {
        this.extraHoursRepository = extraHoursRepository;
        this.employeeService = employeeService;
    }

    public Page<ExtraHours> getAll(String[] sort, Integer[] range, Map<String, String> filter) {
        Pageable paging = PageHelper.getPageable(sort, range);

        Page<ExtraHours> pageAbs;
        if (filter == null) {
            pageAbs = extraHoursRepository.findAll(paging);
        } else {
            if (filter.containsKey("id")) {
                pageAbs = extraHoursRepository.findById(filter.get("id"), paging);
            } else if (filter.containsKey("documentNumber")) {
                pageAbs = extraHoursRepository.findByDocumentNumber(Integer.parseInt(filter.get("documentNumber")), paging);
            } else if (filter.containsKey("q")) {
                pageAbs = extraHoursRepository.findByDocumentNumber(Integer.parseInt(filter.get("q")), paging);
            } else {
                pageAbs = extraHoursRepository.findAll(paging);
            }
        }

        return pageAbs;
    }

    public ExtraHours trackExtraHour(String id, Boolean extraHoursAvailable) {
        Employee employee = employeeService.findById(id);
        List<ExtraHours> extraHoursList = findByEmployeeId(employee.getId());

        ExtraHours extraHours = extraHoursList.stream().reduce((first, second) -> second)
                .orElse(new ExtraHours(employee.getId(), employee.getDocumentNumber(), new Date(), null));

        if (extraHours != null && extraHours.getEnd() == null && !extraHoursAvailable) {
            extraHours.setEnd(new Date());
        }

        return extraHoursRepository.save(extraHours);
    }

    public List<ExtraHours> findByEmployeeId(String employeeId) {
        return extraHoursRepository.findByEmployeeId(employeeId, Sort.by(Sort.Direction.ASC, "employeeId"));
    }
}
