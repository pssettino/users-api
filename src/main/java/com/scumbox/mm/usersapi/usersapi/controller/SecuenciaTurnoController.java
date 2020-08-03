package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.SecuenciaTurno;
import com.scumbox.mm.usersapi.usersapi.service.SecuenciaTurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sequence")
public class SecuenciaTurnoController {
    @Autowired
    private SecuenciaTurnoService secuenciaTurnoService;

    @GetMapping("/")
    public List<SecuenciaTurno> getAll() {
        return secuenciaTurnoService.getAll();
    }


    @PutMapping("/")
    public SecuenciaTurno addSecuenciaTurno(@RequestBody SecuenciaTurno secuenciaTurno) {
        return secuenciaTurnoService.save(secuenciaTurno);
    }

    @GetMapping("/idTurno")
    public SecuenciaTurno findByIdTurno(@RequestParam Integer idTurno){
        return secuenciaTurnoService.findByIdTurno(idTurno);
    }
}
