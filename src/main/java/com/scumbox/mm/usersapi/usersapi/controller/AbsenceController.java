package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {
    private AbsenceService absenceService;
    private EmployeeService employeeService;

    @Autowired
    public AbsenceController(AbsenceService absenceService, EmployeeService employeeService) {
        this.absenceService = absenceService;
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Absence> getAll() {
        return absenceService.getAll();
    }


    @PostMapping("/{id}")
    public Absence addAbsence(@PathVariable String id, @RequestBody Absence absence)
    {
        Employee employee = employeeService.findById(id);
        Absence absenceDb = absenceService.findByDocumentNumber(employee.getDocumentNumber());

        if(absenceDb == null) {
            absence.setDocumentNumber(employee.getDocumentNumber());
            return absenceService.save(absence);
        } else {
            List<AbsenceDetail> detail = absenceDb.getAbsenceDetails();
            if(detail ==  null){
                detail = new ArrayList<>();
            }
            detail.addAll(absence.getAbsenceDetails());

            return absenceService.save(absenceDb);
        }
    }

    @PutMapping("/absence-detail/{id}")
    public Absence addAbsenceDetail(@PathVariable String id,
                                                @RequestBody AbsenceDetail absenceDetail) {
        Absence absence = absenceService.findById(id);
        List<AbsenceDetail> detail = absence.getAbsenceDetails();
        if(detail ==  null){
            detail = new ArrayList<>();
        }
        detail.add(absenceDetail);

        return absenceService.save(absence);
    }

    @GetMapping("/{id}")
    public Absence findById(@PathVariable String id){
        return absenceService.findById(id);
    }

    @GetMapping("/dni/{documentNumber}")
    public Absence findByDocumentNumber(@PathVariable Integer documentNumber) {
        return absenceService.findByDocumentNumber(documentNumber);
    }
}
