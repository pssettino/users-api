package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Employee;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ExtraHoursRepository;
import com.scumbox.mm.usersapi.usersapi.service.EmployeeService;
import com.scumbox.mm.usersapi.usersapi.service.ExtraHoursService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

public class ExtraHourServiceTest {
    ExtraHoursRepository extraHoursRepository = Mockito.mock(ExtraHoursRepository.class);
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    ExtraHoursService extraHoursService = new ExtraHoursService(extraHoursRepository, employeeService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<ExtraHours> extraHour = Optional.of(new ExtraHours());
        List<ExtraHours> extraHours = new ArrayList<>();
        extraHours.add(extraHour.get());
        Page<ExtraHours> page = new PageImpl<ExtraHours>(extraHours);
        String[] sort = {"documentNumber", "asc"};
        // WHEN
        Mockito.when(extraHoursRepository.findAll(page.getPageable())).thenReturn(page);
        Page<ExtraHours> result = extraHoursService.getAll(sort, null, null);

        // THEN
        // Assertions.assertTrue(!result.isEmpty());
    }

    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<ExtraHours> extraHour = Optional.of(new ExtraHours());
        List<ExtraHours> extraHours = new ArrayList<>();
        extraHours.add(extraHour.get());
        Page<ExtraHours> page = new PageImpl<ExtraHours>(extraHours);
        Mockito.when(extraHoursRepository.findByDocumentNumber(33633264, page.getPageable())).thenReturn(page);
        Map<String, String> map = new HashMap<>();
        map.put("documentNumber", "33633264");
        String[] sort = {"documentNumber", "asc"};
        // WHEN
        Page<ExtraHours> result = extraHoursService.getAll(sort, null, map);

        // THEN
        // Assertions.assertTrue(result != null);
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Mockito.when(employeeService.findById(Mockito.any())).thenReturn(new Employee());
        Mockito.when(extraHoursRepository.save(Mockito.any())).thenReturn(new ExtraHours());

        // WHEN
        ExtraHours result = extraHoursService.trackExtraHour("33633624", false);

        // THEN
        Assertions.assertTrue(result != null);
    }
}
