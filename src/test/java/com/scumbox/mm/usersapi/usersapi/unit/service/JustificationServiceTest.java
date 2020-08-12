package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.JustificationRepository;
import com.scumbox.mm.usersapi.usersapi.service.JustificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificationServiceTest {
    JustificationRepository justificationRepository = Mockito.mock(JustificationRepository.class);
    JustificationService justificationService = new JustificationService(justificationRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification());
        List<Justification> justifications = new ArrayList<>();
        justifications.add(justification.get());

        // WHEN
        Mockito.when(justificationRepository.findAll()).thenReturn(justifications);
        List<Justification> result = justificationService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification());
        Mockito.when(justificationRepository.findByDni(Mockito.anyInt())).thenReturn(justification);

        // WHEN
        Justification result = justificationService.findByDni(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(justificationRepository.findByDni(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Justification result = justificationService.findByDni(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification());
        Mockito.when(justificationRepository.save(Mockito.any())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationService.save(justification.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
