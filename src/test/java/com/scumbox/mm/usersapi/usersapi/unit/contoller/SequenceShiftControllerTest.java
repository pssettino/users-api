package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.SequenceShiftController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SequenceShift;
import com.scumbox.mm.usersapi.usersapi.service.SequenceShiftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SequenceShiftControllerTest {
    SequenceShiftService sequenceShiftService = Mockito.mock(SequenceShiftService.class);
    SequenceShiftController sequenceShiftController = new SequenceShiftController(sequenceShiftService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        SequenceShift seq = new SequenceShift();
        seq.setIdTurno(1);
        Optional<SequenceShift> sequenceShift = Optional.of(seq);
        List<SequenceShift> sequenceShifts = new ArrayList<>();
        sequenceShifts.add(sequenceShift.get());
        Mockito.when(sequenceShiftService.getAll()).thenReturn(sequenceShifts);

        // WHEN
        List<SequenceShift> result = sequenceShiftController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByIdTurno_when_has_value() {
        // GIVEN
        SequenceShift seq = new SequenceShift();
        seq.setIdTurno(1);
        Optional<SequenceShift> sequenceShift = Optional.of(seq);
        Mockito.when(sequenceShiftService.findByIdTurno(Mockito.anyInt())).thenReturn(sequenceShift.get());

        // WHEN
        SequenceShift result = sequenceShiftController.findByIdTurno(1);

        // THEN
        Assertions.assertTrue(result.getIdTurno() == 1);
    }

    @Test
    public void test_findByIdTurno_when_hasNot_value() {
        // GIVEN
        Mockito.when(sequenceShiftService.findByIdTurno(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            SequenceShift result = sequenceShiftController.findByIdTurno(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        SequenceShift seq = new SequenceShift();
        seq.setIdTurno(1);
        Optional<SequenceShift> sequenceShift = Optional.of(seq);
        Mockito.when(sequenceShiftService.save(Mockito.any())).thenReturn(sequenceShift.get());

        // WHEN
        SequenceShift result = sequenceShiftController.addSequenceShift(sequenceShift.get());

        // THEN
        Assertions.assertTrue(result.getIdTurno() == 1);
    }
}
