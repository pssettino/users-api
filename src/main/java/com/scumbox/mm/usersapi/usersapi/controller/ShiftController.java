package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "description, asc", required = false) String[] sort,
            @RequestParam(required = false) Integer[] range,
            @RequestParam(required = false) Map<String, String> filter
    ) {

        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();

            Page<Shift> shifts = shiftService.getAll(sort, range, filter);

            response.put("data", shifts.getContent());
            response.put("total", shifts.getTotalElements());
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOne(@PathVariable String id) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            response.put("data", shiftService.findById(id));
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Shift shift) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            shift = shiftService.save(shift);
            response.put("data", shift);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @RequestBody Shift shift) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            shift.setId(id);
            shift = shiftService.save(shift);
            response.put("id", id);
            response.put("data", shift);
            response.put("previousData", shift);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            Shift shift = shiftService.findById(id);
            shift.setId(id);
            shift.setStatus(false);
            shift = shiftService.save(shift);
            response.put("id", id);
            response.put("previousData", shift);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
