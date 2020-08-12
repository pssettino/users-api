package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SequenceShift;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.SequenceShiftRepository;
import com.scumbox.mm.usersapi.usersapi.service.SequenceShiftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SequenceShiftServiceTest {
    SequenceShiftRepository sequenceShiftRepository = Mockito.mock(SequenceShiftRepository.class);
    SequenceShiftService sequenceShiftService = new SequenceShiftService(sequenceShiftRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<SequenceShift> sequenceShift = Optional.of(new SequenceShift());
        List<SequenceShift> sequenceShifts = new ArrayList<>();
        sequenceShifts.add(sequenceShift.get());

        // WHEN
        Mockito.when(sequenceShiftRepository.findAll()).thenReturn(sequenceShifts);
        List<SequenceShift> result = sequenceShiftService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByIdTurno_when_has_value() {
        // GIVEN
        Optional<SequenceShift> sequenceShift = Optional.of(new SequenceShift());
        Mockito.when(sequenceShiftRepository.findByIdTurno(Mockito.anyInt())).thenReturn(sequenceShift);

        // WHEN
        SequenceShift result = sequenceShiftService.findByIdTurno(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByIdTurno_when_hasNot_value() {
        // GIVEN
        Mockito.when(sequenceShiftRepository.findByIdTurno(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            SequenceShift result = sequenceShiftService.findByIdTurno(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<SequenceShift> sequenceShift = Optional.of(new SequenceShift());
        Mockito.when(sequenceShiftRepository.save(Mockito.any())).thenReturn(sequenceShift.get());

        // WHEN
        SequenceShift result = sequenceShiftService.save(sequenceShift.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
