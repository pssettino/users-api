package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.AbsenceController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AbsenceControllerTest {
    
    AbsenceService absenceService = Mockito.mock(AbsenceService.class);
    AbsenceController absenceControllerTest = new AbsenceController(absenceService);

    private static List<AbsenceDetail> absencesDetailMock(){
        List<AbsenceDetail> absenceDetails = new ArrayList<AbsenceDetail>();

        AbsenceDetail detail = new AbsenceDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setType("JUSTIFICATION");
        detail.setDescription("Me quede dormido");
        absenceDetails.add(detail);
        return absenceDetails;
    }

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN


        Optional<Absence> absence = Optional.of(new Absence(33633264, absencesDetailMock()));

        List<Absence> absences = new ArrayList<>();
        absences.add(absence.get());
        Mockito.when(absenceService.getAll()).thenReturn(absences);

        // WHEN
        List<Absence> result = absenceControllerTest.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Absence> absence = Optional.of(new Absence(33633264, absencesDetailMock()));
        Mockito.when(absenceService.findByDocumentNumber(Mockito.anyInt())).thenReturn(absence.get());

        // WHEN
        Absence result = absenceControllerTest.findByDocumentNumber(33633264);

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(absenceService.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            Absence result = absenceControllerTest.findByDocumentNumber(33633265);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Absence> absence = Optional.of(new Absence(33633264, absencesDetailMock()));
        Mockito.when(absenceService.save(Mockito.any())).thenReturn(absence.get());

        // WHEN
        Absence result = absenceControllerTest.addAbsence(absence.get());

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }

    @Test
    public void test_save_addAbsenceDetail_is_ok() {
        // GIVEN
        AbsenceDetail detail = new AbsenceDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setType("JUSTIFICATION");
        detail.setDescription("Me quede dormido");

        Optional<Absence> absence = Optional.of(new Absence(33633264, absencesDetailMock()));
        Mockito.when(absenceService.findByDocumentNumber(Mockito.anyInt())).thenReturn(absence.get());
        Mockito.when(absenceService.save(Mockito.any())).thenReturn(absence.get());

        // WHEN
        Absence result = absenceControllerTest.addAbsenceDetail(33633264, detail);

        // THEN
        Assertions.assertTrue(result.getDocumentNumber() == 33633264);
    }
}