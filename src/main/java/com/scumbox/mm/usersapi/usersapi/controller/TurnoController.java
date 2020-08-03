package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Turno;
import com.scumbox.mm.usersapi.usersapi.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @GetMapping("/")
    public List<Turno> getAll() {
        return turnoService.getAll();
    }


    @PutMapping("/")
    public Turno addSecuenciaTurno(@RequestBody Turno turno) {
        return turnoService.save(turno);
    }

    @GetMapping("/idTurno")
    public Turno findByIdTurno(@RequestParam Integer idTurno){
        return turnoService.findByIdTurno(idTurno);
    }
}
