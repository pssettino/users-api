package com.scumbox.mm.usersapi.usersapi.controller;

import com.google.common.base.Strings;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceDetailService;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/absencesDetails")
public class AbsenceDetailController {
    private AbsenceDetailService absenceDetailService;

    @Autowired
    public AbsenceDetailController(AbsenceDetailService absenceDetailService) {
        this.absenceDetailService = absenceDetailService;
    }

    @GetMapping("")
    public List<AbsenceDetail> getAll(@RequestParam(required = false) String employeeId) {
        if(Strings.isNullOrEmpty(employeeId)){
            return absenceDetailService.getAll();
        }
        return absenceDetailService.getAll().stream().filter(it ->
                it.getStatus() && employeeId.equals(it.getEmployeeId())
        ).collect(Collectors.toList());
    }

    @PostMapping("/{employeeId}")
    public void addAbsence(@PathVariable String employeeId, @RequestBody AbsenceDetail absenceDetail) {
        absenceDetail.setEmployeeId(employeeId);
        absenceDetailService.save(absenceDetail);
    }

    @GetMapping("/id")
    public AbsenceDetail findById(@RequestParam String id){
        return absenceDetailService.findById(id);
    }

    @GetMapping("/{employeeId}/absences")
    public List<AbsenceDetail> findByEmployeeId(@PathVariable String employeeId) {
        return absenceDetailService.findByEmployeeId(employeeId);
    }
}
