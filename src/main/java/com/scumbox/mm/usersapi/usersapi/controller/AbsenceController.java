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


    @PostMapping("/{id}")
    public void addAbsence(@PathVariable String id, @RequestBody AbsenceDetail absenceDetail)
    {
        Employee employee = employeeService.findById(id);
        Absence absenceDb = absenceService.findByDocumentNumber(employee.getDocumentNumber());
        absenceDetail.setStatus(true);
        if(absenceDb == null) {
            Absence absence = new Absence();
            absence.setDocumentNumber(employee.getDocumentNumber());
            absence.setEmployeeId(employee.getId());
            absence = absenceService.save(absence);
            absenceDetail.setAbsenceId(absence.getId());
            absenceDetailService.save(absenceDetail);
        } else {
            absenceDetail.setAbsenceId(absenceDb.getId());
            absenceDetailService.save(absenceDetail);
        }
    }

    @GetMapping("/id")
    public Absence findById(@RequestParam String id){
        return absenceService.findById(id);
    }

    @GetMapping("/dni/{documentNumber}")
    public Absence findByDocumentNumber(@PathVariable Integer documentNumber) {
        return absenceService.findByDocumentNumber(documentNumber);
    }

    @GetMapping("/{employeeId}")
    public List<AbsenceDetail> findByEmployeeId(@PathVariable String employeeId) {
        Employee employee = employeeService.findById(employeeId);
        Absence absenceDb = absenceService.findByDocumentNumber(employee.getDocumentNumber());

        if(absenceDb == null) {
            return new ArrayList<>();
        }
        Integer i = 0;
        List<AbsenceDetail> detail = absenceDetailService.findByAbsenceId(absenceDb.getId()).stream().filter(it ->
                it.getStatus()).collect(Collectors.toList()
        );

        return detail;
    }
}
