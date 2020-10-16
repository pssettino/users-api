package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceDetailRepository;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceDetailService;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {
    private AbsenceService absenceService;
    private EmployeeService employeeService;
    private AbsenceDetailService absenceDetailService;

    @Autowired
    public AbsenceController(AbsenceService absenceService, EmployeeService employeeService, AbsenceDetailService absenceDetailService) {
        this.absenceService = absenceService;
        this.employeeService = employeeService;
        this.absenceDetailService = absenceDetailService;
    }

    @GetMapping("")
    public List<Absence> getAll() {
        return absenceService.getAll();
    }


    @GetMapping("/id")
    public Absence findById(@RequestParam String id){
        return absenceService.findById(id);
    }

    @GetMapping("/dni/{documentNumber}")
    public Absence findByDocumentNumber(@PathVariable Integer documentNumber) {
        return absenceService.findByDocumentNumber(documentNumber);
    }

}
