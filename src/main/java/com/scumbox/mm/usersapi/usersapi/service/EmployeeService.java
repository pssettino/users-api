package com.scumbox.mm.usersapi.usersapi.service;

import com.netflix.discovery.converters.Auto;
import com.scumbox.mm.usersapi.usersapi.PageHelper;
import com.scumbox.mm.usersapi.usersapi.exception.DuplicateEmployeeException;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.EmployeeRepository;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ExtraHoursService extraHoursService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Page<Employee> getAll(String[] sort, Integer[] range, Map<String, String> filter) {
        Pageable paging = PageHelper.getPageable(sort, range);

        Page<Employee> pageAbs;
        if (filter == null) {
            pageAbs = employeeRepository.findAll(paging);
        } else {
            if(filter.containsKey("id"))  {
                pageAbs = employeeRepository.findById(filter.get("id"), paging);
            } else if(filter.containsKey("documentNumber")) {
                pageAbs = employeeRepository.findByDocumentNumber(Integer.parseInt(filter.get("documentNumber")), paging);
            } else if(filter.containsKey("q")) {
                pageAbs = employeeRepository.findByFullNameContaining(filter.get("q"), paging);
            } else {
                pageAbs = employeeRepository.findAll(paging);
            }
        }

        return pageAbs;
    }

    @CachePut(value = "employees")
    public Employee save(Employee employee) {
        Boolean exist = existDocumentNumber(employee.getDocumentNumber());

        if(exist) { throw new DuplicateEmployeeException(); }

        return employeeRepository.save(employee);
    }

    @Cacheable(value = "employees")
    public Employee findById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElseThrow(NotFoundException::new);
    }

    private Boolean existDocumentNumber(Integer documentNumber) {
        List<Employee> employee = employeeRepository.findByDocumentNumber(documentNumber, Sort.by(Sort.Direction.ASC, "documentNumber"));

        return !employee.isEmpty();
    }
}

