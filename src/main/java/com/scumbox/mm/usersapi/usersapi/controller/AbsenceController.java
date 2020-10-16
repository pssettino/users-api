package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.dto.AbsenceDetailDto;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
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
    public Absence addAbsence(@PathVariable String id, @RequestBody AbsenceDetail absenceDetail)
    {
        Employee employee = employeeService.findById(id);
        Absence absenceDb = absenceService.findByDocumentNumber(employee.getDocumentNumber());

        if(absenceDb == null) {
            Absence absence = new Absence();
            absence.setDocumentNumber(employee.getDocumentNumber());
            absence.setEmployeeId(employee.getId());
            List<AbsenceDetail> detail = new ArrayList<>();
            absenceDetail.setStatus(true);
            detail.add(absenceDetail);
            return absenceService.save(absence);
        } else {
            List<AbsenceDetail> detail = absenceDb.getAbsenceDetails();
            detail.add(absenceDetail);

            return absenceService.save(absenceDb);
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
    public List<AbsenceDetailDto> findByEmployeeId(@PathVariable String employeeId) {
        Employee employee = employeeService.findById(employeeId);
        Absence absenceDb = absenceService.findByDocumentNumber(employee.getDocumentNumber());

        if(absenceDb == null) {
            return new ArrayList<>();
        }
        Integer i = 0;
        List<AbsenceDetail> detail = absenceDb.getAbsenceDetails();

        // TODO: MEJORAR ESTA GRASADA
        List<AbsenceDetailDto> list = new ArrayList<>();
        for (AbsenceDetail it: detail) {
            AbsenceDetailDto dto = new AbsenceDetailDto();
            dto.setId(i++);
            dto.setEmployeeId(employeeId);
            dto.setStart(it.getStart());
            dto.setEnd(it.getEnd());
            dto.setType(it.getType());
            dto.setDescription(it.getDescription());
            dto.setStatus(it.getStatus());

            list.add(dto);
        }

        return list;
    }
}
