package com.scumbox.mm.usersapi.usersapi.controller;

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
    public List<AbsenceDetail> getAll(@RequestParam(required = false) String absenceId) {
        return absenceDetailService.getAll().stream().filter(it ->
                it.getStatus() && absenceId.equals(it.getAbsenceId())
        ).collect(Collectors.toList());
    }


    @PostMapping("/{id}")
    public void add(@PathVariable String id, @RequestBody AbsenceDetail absenceDetail) {
        absenceDetailService.save(absenceDetail);
    }

    @GetMapping("/id")
    public AbsenceDetail findById(@RequestParam String id){
        return absenceDetailService.findById(id);
    }

    @GetMapping("/{absenceId}/absence")
    public List<AbsenceDetail> findByAbsenceId(@PathVariable String absenceId) {
        return absenceDetailService.findByAbsenceId(absenceId);
    }
}
