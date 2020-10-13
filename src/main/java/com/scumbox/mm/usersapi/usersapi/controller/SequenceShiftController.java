package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.SequenceShift;
import com.scumbox.mm.usersapi.usersapi.service.SequenceShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sequence")
public class SequenceShiftController {

    private SequenceShiftService sequenceShiftService;

    @Autowired
    public SequenceShiftController(SequenceShiftService sequenceShiftService) {
        this.sequenceShiftService = sequenceShiftService;
    }

    @GetMapping("")
    public List<SequenceShift> getAll() {
        return sequenceShiftService.getAll();
    }


    @PostMapping("")
    public SequenceShift addSequenceShift(@RequestBody SequenceShift sequenceShift) {
        return sequenceShiftService.save(sequenceShift);
    }

    @GetMapping("/{shiftId}")
    public SequenceShift findByShiftId(@PathVariable Integer shiftId){
        return sequenceShiftService.findByShiftId(shiftId);
    }
}
