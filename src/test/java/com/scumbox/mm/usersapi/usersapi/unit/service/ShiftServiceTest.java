package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Shift;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ShiftRepository;
import com.scumbox.mm.usersapi.usersapi.service.ShiftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiftServiceTest {
    ShiftRepository shiftRepository = Mockito.mock(ShiftRepository.class);
    ShiftService shiftService = new ShiftService(shiftRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Shift> shift = Optional.of(new Shift());
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shift.get());
        Page<Shift> page = new PageImpl<Shift>(shifts);
        String[] sort = {"id", "asc"};
        // WHEN
        Mockito.when(shiftRepository.findAll(page.getPageable())).thenReturn(page);
        Page<Shift> result = shiftService.getAll(sort, null, null);

        // THEN
        // Assertions.assertTrue(!result.isEmpty());
    }


    @Test
    public void test_findByIdTurno_when_has_value() {
        // GIVEN
        Optional<Shift> shift = Optional.of(new Shift());
        Mockito.when(shiftRepository.findById(Mockito.anyString())).thenReturn(shift);

        // WHEN
        Shift result = shiftService.findById("1");

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByIdTurno_when_hasNot_value() {
        // GIVEN
        Mockito.when(shiftRepository.findById(Mockito.anyString())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Shift result = shiftService.findById("1");

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Shift> shift = Optional.of(new Shift());
        Mockito.when(shiftRepository.save(Mockito.any())).thenReturn(shift.get());

        // WHEN
        Shift result = shiftService.save(shift.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
