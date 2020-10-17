package com.scumbox.mm.usersapi.usersapi.controller;

import com.google.common.base.Strings;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceDetailService;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/absences")
public class AbsenceDetailController {
    private AbsenceDetailService absenceDetailService;

    @Autowired
    public AbsenceDetailController(AbsenceDetailService absenceDetailService) {
        this.absenceDetailService = absenceDetailService;
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

            Page<AbsenceDetail> absences = absenceDetailService.getAll(sort, range, filter);

            response.put("data", absences.getContent());
            response.put("total", absences.getTotalElements());
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
            response = new HashMap<String, Object>();
            response.put("data", absenceDetailService.findById(id));
            response.put("validUntil", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<Map<String, Object>> create(@PathVariable String employeeId, @RequestBody AbsenceDetail absenceDetail) {
        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();
            absenceDetail.setEmployeeId(employeeId);
            absenceDetail.setStatus(true);
            absenceDetailService.save(absenceDetail);

            response.put("data", absenceDetail);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @RequestBody AbsenceDetail absenceDetail) {
        Map<String, Object> response;
        try {
            response = new HashMap<String, Object>();
            //AbsenceDetail absenceDetailDb = absenceDetailService.findById(id);

            AbsenceDetail previousData = absenceDetail;
            absenceDetail.setId(id);
            absenceDetail.setEmployeeId(absenceDetail.getEmployeeId());
            absenceDetailService.save(absenceDetail);

            response.put("id", id);
            response.put("data", absenceDetail);
            response.put("previousData", previousData);

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
            response = new HashMap<String, Object>();
            AbsenceDetail absenceDetail = absenceDetailService.findById(id);
            AbsenceDetail previousData = absenceDetail;
            absenceDetail.setStatus(false);
            absenceDetailService.save(absenceDetail);
            response.put("id", id);
            response.put("data", absenceDetail);
            response.put("previousData", previousData);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new HashMap<String, Object>();
            response.put("error:", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
