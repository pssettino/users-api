package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ExtraHoursService {

    private ExtraHoursRepository extraHoursRepository;
    private EmployeeService employeeService;

    @Autowired
    public ExtraHoursService(ExtraHoursRepository extraHoursRepository, EmployeeService employeeService) {
        this.extraHoursRepository = extraHoursRepository;
        this.employeeService = employeeService;
    }

    public List<ExtraHours> getAll() {
        return extraHoursRepository.findAll();
    }

    public ExtraHours trackExtraHour(Integer documentNumber, Boolean extraHoursAvailable) {
        Employee employee = employeeService.findByDocumentNumber(documentNumber);
        List<ExtraHours> extraHoursList = findByDocumentNumber(employee.getDocumentNumber());

        ExtraHours extraHours = extraHoursList.stream().reduce((first, second) -> second)
                .orElse(new ExtraHours(employee.getDocumentNumber(), new Date(), null));

        if(extraHours != null && extraHours.getEnd() == null && !extraHoursAvailable) {
            extraHours.setEnd(new Date());
        }

        return extraHoursRepository.save(extraHours);
    }

    public List<ExtraHours> findByDocumentNumber(Integer documentNumber) {
        return extraHoursRepository.findByDocumentNumber(documentNumber);
    }
}
