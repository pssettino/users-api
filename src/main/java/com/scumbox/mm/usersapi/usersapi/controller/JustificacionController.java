package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justificacion;
import com.scumbox.mm.usersapi.usersapi.service.JustificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/justification")
public class JustificacionController {
    @Autowired
    private JustificacionService justificacionService;

    @GetMapping("/")
    public List<Justificacion> getAll() {
        return justificacionService.getAll();
    }


    @PutMapping("/")
    public Justificacion addJusticacion(@RequestBody Justificacion justificacion) {
        return justificacionService.save(justificacion);
    }

    @GetMapping("/dni")
    public Justificacion findByDni(@RequestParam Integer dni) {
        return justificacionService.findByDni(dni);
    }
}

