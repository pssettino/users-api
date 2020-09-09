package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Sanction;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SanctionDetail;
import com.scumbox.mm.usersapi.usersapi.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/santion")
public class SanctionController {
    private SanctionService sanctionService;

    @Autowired
    public SanctionController(SanctionService sanctionService) {
        this.sanctionService = sanctionService;
    }

    @GetMapping("/")
    public List<Sanction> getAll() {
        return sanctionService.getAll();
    }


    @PutMapping("/")
    public Sanction addSantion(@RequestBody Sanction sanction) {
        return sanctionService.save(sanction);
    }

    @PutMapping("/{dni}")
    public Sanction addSantionDetail(@PathVariable Integer dni,
                                                @RequestBody SanctionDetail sanctionDetail) {
        Sanction sanction = sanctionService.findByDni(dni);

        List<SanctionDetail> detail = sanction.getSantionDetail();
        if(detail ==  null){
            detail = new ArrayList<>();
        }
        detail.add(sanctionDetail);

        return sanctionService.save(sanction);
    }

    @GetMapping("/dni")
    public Sanction findByDni(@RequestParam Integer dni) {
        return sanctionService.findByDni(dni);
    }
}
