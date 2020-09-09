package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.JustificationController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Justification;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.JustificationDetail;
import com.scumbox.mm.usersapi.usersapi.service.JustificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JustificationControllerTest {
    JustificationService justificationService = Mockito.mock(JustificationService.class);
    JustificationController justificationController = new JustificationController(justificationService);

    private static List<JustificationDetail> justificationDetailMock(){
        List<JustificationDetail> justificationDetailList = new ArrayList<JustificationDetail>();

        JustificationDetail detail = new JustificationDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setDescription("Me quede dormido");
        justificationDetailList.add(detail);
        return justificationDetailList;
    }

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN


        Optional<Justification> justification = Optional.of(new Justification(33633264, justificationDetailMock()));

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
        Optional<Justification> justification = Optional.of(new Justification(33633264, justificationDetailMock()));
        Mockito.when(justificationService.findByDocumentNumber(Mockito.anyInt())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationController.findByDocumentNumber(33633264);

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(justificationService.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Justification result = justificationController.findByDocumentNumber(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Justification> justification = Optional.of(new Justification(33633264, justificationDetailMock()));
        Mockito.when(justificationService.save(Mockito.any())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationController.addJusticacion(justification.get());

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }

    @Test
    public void test_save_addJustificationDetail_is_ok() {
        // GIVEN
        JustificationDetail detail = new JustificationDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setDescription("Me quede dormido");

        Optional<Justification> justification = Optional.of(new Justification(33633264, justificationDetailMock()));
        Mockito.when(justificationService.findByDocumentNumber(Mockito.anyInt())).thenReturn(justification.get());
        Mockito.when(justificationService.save(Mockito.any())).thenReturn(justification.get());

        // WHEN
        Justification result = justificationController.addJustificationDetail(33633264, detail);

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }
}
