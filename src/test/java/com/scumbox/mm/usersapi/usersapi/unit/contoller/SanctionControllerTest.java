package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.SanctionController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Sanction;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.SanctionDetail;
import com.scumbox.mm.usersapi.usersapi.service.SanctionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SanctionControllerTest {
    SanctionService sanctionService = Mockito.mock(SanctionService.class);
    SanctionController sanctionController = new SanctionController(sanctionService);

    private static List<SanctionDetail> sanctionDetailMock(){
        List<SanctionDetail> sanctionDetails = new ArrayList<SanctionDetail>();

        SanctionDetail detail = new SanctionDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setDescription("Me quede dormido");
        sanctionDetails.add(detail);
        return sanctionDetails;
    }

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN


        Optional<Sanction> sanction = Optional.of(new Sanction("345464", 33633264, sanctionDetailMock()));

        List<Sanction> sanctions = new ArrayList<>();
        sanctions.add(sanction.get());
        Mockito.when(sanctionService.getAll()).thenReturn(sanctions);

        // WHEN
        List<Sanction> result = sanctionController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Sanction> sanction = Optional.of(new Sanction("345464", 33633264, sanctionDetailMock()));
        Mockito.when(sanctionService.findByDni(Mockito.anyInt())).thenReturn(sanction.get());

        // WHEN
        Sanction result = sanctionController.findByDni(33633264);

        // THEN
        Assertions.assertTrue(result.getDni() == 33633264);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(sanctionService.findByDni(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Sanction result = sanctionController.findByDni(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Sanction> sanction = Optional.of(new Sanction("345464", 33633264, sanctionDetailMock()));
        Mockito.when(sanctionService.save(Mockito.any())).thenReturn(sanction.get());

        // WHEN
        Sanction result = sanctionController.addSantion(sanction.get());

        // THEN
        Assertions.assertTrue(result.getDni() == 33633264);
    }

    @Test
    public void test_save_addSanctionDetail_is_ok() {
        // GIVEN
        SanctionDetail detail = new SanctionDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setDescription("Me quede dormido");

        Optional<Sanction> sanction = Optional.of(new Sanction("345464", 33633264, sanctionDetailMock()));
        Mockito.when(sanctionService.findByDni(Mockito.anyInt())).thenReturn(sanction.get());
        Mockito.when(sanctionService.save(Mockito.any())).thenReturn(sanction.get());

        // WHEN
        Sanction result = sanctionController.addSantionDetail(33633264, detail);

        // THEN
        Assertions.assertTrue(result.getDni() == 33633264);
    }
}
