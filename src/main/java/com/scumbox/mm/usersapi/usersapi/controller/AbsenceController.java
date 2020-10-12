package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {
    private AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/")
    public List<Absence> getAll() {
        return absenceService.getAll();
    }


    @PostMapping("/")
    public Absence addAbsence(@RequestBody Absence absence) {
        return absenceService.save(absence);
    }

    @PutMapping("/{id}")
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

    @GetMapping("/{documentNumber}")
    public Absence findByDocumentNumber(@PathVariable Integer documentNumber) {
        return absenceService.findByDocumentNumber(documentNumber);
    }
}
