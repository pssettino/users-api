package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.JustificationDetail;
import com.scumbox.mm.usersapi.usersapi.service.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/justification")
public class JustificationController {

    private JustificationService justificationService;

    @Autowired
    public JustificationController(JustificationService justificationService) {
        this.justificationService = justificationService;
    }

    @GetMapping("/")
    public List<Justification> getAll() {
        return justificationService.getAll();
    }


    @PutMapping("/")
    public Justification addJusticacion(@RequestBody Justification justification) {
        return justificationService.save(justification);
    }

    @PutMapping("/{documentNumber}")
    public Justification addJustificationDetail(@PathVariable Integer documentNumber,
                                                @RequestBody JustificationDetail justificationDetail) {
        Justification justification = justificationService.findByDocumentNumber(documentNumber);

        List<JustificationDetail> detail = justification.getJustificationDetail();
        if(detail ==  null){
            detail = new ArrayList<>();
        }
        detail.add(justificationDetail);

        return justificationService.save(justification);
    }

    @GetMapping("/documentNumber")
    public Justification findByDocumentNumber(@RequestParam Integer documentNumber) {
        return justificationService.findByDocumentNumber(documentNumber);
    }
}

