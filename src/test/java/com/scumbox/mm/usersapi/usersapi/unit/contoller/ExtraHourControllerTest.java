package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.ExtraHoursController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
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
        Page<ExtraHours> page = new PageImpl<ExtraHours>(extHrs);
        Mockito.when(extraHoursService.getAll(null,null,null)).thenReturn(page);

        // WHEN
        ResponseEntity<Map<String, Object>> result = extraHoursController.getList(null,null,null);

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Mockito.when(extraHoursService.trackExtraHour(Mockito.any(), Mockito.anyBoolean())).thenReturn(new ExtraHours());
        // WHEN
        ResponseEntity<Map<String, Object>> result = extraHoursController.trackExtraHour("safsd", true);

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }
}
