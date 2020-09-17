package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.AbsenceRepository;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbsenceServiceTest {
    AbsenceRepository absenceRepository = Mockito.mock(AbsenceRepository.class);
    AbsenceService absenceService = new AbsenceService(absenceRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Absence> absence = Optional.of(new Absence());
        List<Absence> absences = new ArrayList<>();
        absences.add(absence.get());

        // WHEN
        Mockito.when(absenceRepository.findAll()).thenReturn(absences);
        List<Absence> result = absenceService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Absence> absence = Optional.of(new Absence());
        Mockito.when(absenceRepository.findByDocumentNumber(Mockito.anyInt())).thenReturn(absence);

        // WHEN
        Absence result = absenceService.findByDocumentNumber(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(absenceRepository.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Absence result = absenceService.findByDocumentNumber(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Absence> absence = Optional.of(new Absence());
        Mockito.when(absenceRepository.save(Mockito.any())).thenReturn(absence.get());

        // WHEN
        Absence result = absenceService.save(absence.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
