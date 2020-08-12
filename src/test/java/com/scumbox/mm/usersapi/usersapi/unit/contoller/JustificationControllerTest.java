package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.JustificationController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import com.scumbox.mm.usersapi.usersapi.service.JustificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificationControllerTest {
    JustificationService justificationService = Mockito.mock(JustificationService.class);
    JustificationController justificationController = new JustificationController(justificationService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification("345464", 33633264, "me quede dormido"));
        List<Justification> justifications = new ArrayList<>();
        justifications.add(justification.get());
        Mockito.when(justificationService.getAll()).thenReturn(justifications);

        // WHEN
        List<Justification> result = justificationController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification("345464", 33633264, "me quede dormido"));
        Mockito.when(justificationService.findByDni(Mockito.anyInt())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationController.findByDni(33633264);

        // THEN
        Assertions.assertTrue(result.getDni() == 33633264);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(justificationService.findByDni(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Justification result = justificationController.findByDni(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification("345464", 33633264, "me quede dormido"));
        Mockito.when(justificationService.save(Mockito.any())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationController.addJusticacion(justification.get());

        // THEN
        Assertions.assertTrue(result.getDni() == 33633264);
    }
}
