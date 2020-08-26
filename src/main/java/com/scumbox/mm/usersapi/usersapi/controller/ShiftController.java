package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("/")
    public List<Shift> getAll() {
        return shiftService.getAll();
    }


    @PutMapping("/")
    public Shift addShift(@RequestBody Shift shift) {
        return shiftService.save(shift);
    }

    @GetMapping("/shiftId")
    public Shift findByShiftId(@RequestParam Integer shiftId){
        return shiftService.findByShiftId(shiftId);
    }
}