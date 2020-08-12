package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.ShiftController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.service.ShiftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiftControllerTest {
    private ShiftService shiftService = Mockito.mock(ShiftService.class);
    private ShiftController shiftController = new ShiftController(shiftService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Shift shift = new Shift();
        shift.setIdTurno(1);
        Optional<Shift> shiftOptional = Optional.of(shift);
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shiftOptional.get());
        Mockito.when(shiftService.getAll()).thenReturn(shifts);

        // WHEN
        List<Shift> result = shiftController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByIdTurno_when_has_value() {
        // GIVEN
        Shift seq = new Shift();
        seq.setIdTurno(1);
        Optional<Shift> shiftOptional = Optional.of(seq);
        Mockito.when(shiftService.findByIdTurno(Mockito.anyInt())).thenReturn(shiftOptional.get());

        // WHEN
        Shift result = shiftController.findByIdTurno(1);

        // THEN
        Assertions.assertTrue(result.getIdTurno() == 1);
    }

    @Test
    public void test_findByIdTurno_when_hasNot_value() {
        // GIVEN
        Mockito.when(shiftService.findByIdTurno(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Shift result = shiftController.findByIdTurno(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Shift seq = new Shift();
        seq.setIdTurno(1);
        Optional<Shift> sequenceShift = Optional.of(seq);
        Mockito.when(shiftService.save(Mockito.any())).thenReturn(sequenceShift.get());

        // WHEN
        Shift result = shiftController.addShift(sequenceShift.get());

        // THEN
        Assertions.assertTrue(result.getIdTurno() == 1);
    }
}
