package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.ShiftController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.service.ShiftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShiftControllerTest {
    private ShiftService shiftService = Mockito.mock(ShiftService.class);
    private ShiftController shiftController = new ShiftController(shiftService);


    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Shift shift = new Shift();
        shift.setId("1");
        Optional<Shift> shiftOptional = Optional.of(shift);
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shiftOptional.get());
        Page<Shift> page = new PageImpl<Shift>(shifts);

        Mockito.when(shiftService.getAll(null, null, null)).thenReturn(page);

        // WHEN
        ResponseEntity<Map<String, Object>> result = shiftController.getList(null, null, null);

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void test_findByIdTurno_when_has_value() {
        // GIVEN
        Shift seq = new Shift();
        seq.setId("1");
        Optional<Shift> shiftOptional = Optional.of(seq);
        Mockito.when(shiftService.findById(Mockito.anyString())).thenReturn(shiftOptional.get());

        // WHEN
        ResponseEntity<Map<String, Object>> result = shiftController.getOne("1");

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_findByIdTurno_when_hasNot_value() {
        // GIVEN
        Mockito.when(shiftService.findById(Mockito.anyString())).thenThrow(NotFoundException.class);

        //WHEN
        try {
            shiftController.getOne(null);

        } catch (Exception nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Shift seq = new Shift();
        seq.setId("1");
        Optional<Shift> sequenceShift = Optional.of(seq);
        Mockito.when(shiftService.save(Mockito.any())).thenReturn(sequenceShift.get());

        // WHEN
        ResponseEntity<Map<String, Object>> result = shiftController.create(sequenceShift.get());

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }
}
