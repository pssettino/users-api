package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extra-hour")
public class ExtraHoursController {
    private ExtraHoursService extraHoursService;

    @Autowired
    public ExtraHoursController(ExtraHoursService extraHoursService) {
        this.extraHoursService = extraHoursService;
    }


    @GetMapping("")
    public @ResponseBody
    ResponseEntity<List<ExtraHours>> getAll() {
        List<ExtraHours> extraHoursList = extraHoursService.getAll();

        return ResponseEntity.ok(extraHoursList);
    }


    @PostMapping("/{documentNumber}")
    public @ResponseBody ExtraHours trackExtraHour(@PathVariable Integer documentNumber,
                                                   @RequestParam Boolean extraHoursAvailable) {
        return extraHoursService.trackExtraHour(documentNumber, extraHoursAvailable);
    }
    

    @GetMapping("/{documentNumber}")
    public @ResponseBody List<ExtraHours> findByDocumentNumber(@PathVariable Integer documentNumber) {
        return extraHoursService.findByDocumentNumber(documentNumber);
    }
}
