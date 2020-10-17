package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/extra-hour")
public class ExtraHoursController {
    private ExtraHoursService extraHoursService;

    @Autowired
    public ExtraHoursController(ExtraHoursService extraHoursService) {
        this.extraHoursService = extraHoursService;
    }


    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "id, asc", required = false) String[] sort,
            @RequestParam(required = false) Integer[] range,
            @RequestParam(required = false) Map<String, String> filter
    ) {
        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();

            Page<ExtraHours> extraHoursList = extraHoursService.getAll(sort, range, filter);

            response.put("data", extraHoursList.getContent());
            response.put("total", extraHoursList.getTotalElements());
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{employeeId}")
    public ResponseEntity<Map<String, Object>> trackExtraHour(@PathVariable String employeeId,
                                                              @RequestParam Boolean extraHoursAvailable) {
        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();
            ExtraHours extraHour = extraHoursService.trackExtraHour(employeeId, extraHoursAvailable);
            response.put("data", extraHour);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
