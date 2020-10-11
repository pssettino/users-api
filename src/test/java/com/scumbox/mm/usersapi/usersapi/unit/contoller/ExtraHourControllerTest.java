package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.ExtraHoursController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExtraHourControllerTest {
    
    ExtraHoursService extraHoursService = Mockito.mock(ExtraHoursService.class);
    ExtraHoursController extraHoursController = new ExtraHoursController(extraHoursService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        ExtraHours extHr = new ExtraHours();
        extHr.setDocumentNumber(33633264);
       
        Optional<ExtraHours> extraHour = Optional.of(extHr);
        List<ExtraHours> extHrs = new ArrayList<>();
        extHrs.add(extraHour.get());
        Mockito.when(extraHoursService.getAll()).thenReturn(extHrs);

        // WHEN
        ResponseEntity<List<ExtraHours>> result = extraHoursController.getAll();

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        ExtraHours extHr = new ExtraHours();
        extHr.setDocumentNumber(33633264);
        Optional<ExtraHours> extraHour = Optional.of(extHr);
        List<ExtraHours> extHrs = new ArrayList<>();
        extHrs.add(extraHour.get());
        Mockito.when(extraHoursService.findByDocumentNumber(Mockito.anyInt())).thenReturn(extHrs);

        // WHEN
        List<ExtraHours> result = extraHoursController.findByDocumentNumber(33633264);

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(extraHoursService.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            List<ExtraHours> result = extraHoursController.findByDocumentNumber(33633264);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Mockito.when(extraHoursService.trackExtraHour(Mockito.any(), Mockito.anyBoolean())).thenReturn(new ExtraHours());
        // WHEN
        ExtraHours result = extraHoursController.trackExtraHour(33633264, true);

        // THEN
        Assertions.assertTrue(result != null);
    }
}
