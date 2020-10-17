package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.AbsenceDetailController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Absence;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.AbsenceDetail;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceDetailService;
import com.scumbox.mm.usersapi.usersapi.service.AbsenceService;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class AbsenceControllerTest {

    AbsenceDetailService absenceDetailService = Mockito.mock(AbsenceDetailService.class);
    AbsenceDetailController absenceDetailControllerTest = new AbsenceDetailController(absenceDetailService);

    private static List<AbsenceDetail> absencesDetailMock(){
        List<AbsenceDetail> absenceDetails = new ArrayList<AbsenceDetail>();

        AbsenceDetail detail = new AbsenceDetail();
        detail.setStart(new Date());
        detail.setEnd(new Date());
        detail.setType("JUSTIFICATION");
        detail.setDescription("Me quede dormido");
        detail.setStatus(true);
        absenceDetails.add(detail);
        return absenceDetails;
    }

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN


        Page<AbsenceDetail> page = new PageImpl<AbsenceDetail>(absencesDetailMock());

        String[] sort = {"employeeId", "ASC"};
        Integer[] range = {0, 3};
        Map<String, String> filter = new HashMap<>();
        filter.put("q", "4546786");

        Mockito.when(absenceDetailService.getAll(sort, range, filter)).thenReturn(page);



        // WHEN
        ResponseEntity<Map<String, Object>> result = absenceDetailControllerTest.getList(sort, range, filter);

        // THEN
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
    }

}
