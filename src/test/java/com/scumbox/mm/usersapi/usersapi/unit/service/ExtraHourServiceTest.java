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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        // WHEN
        Mockito.when(extraHoursRepository.findAll()).thenReturn(extraHours);
        List<ExtraHours> result = extraHoursService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }

    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<ExtraHours> extraHour = Optional.of(new ExtraHours());
        List<ExtraHours> extraHours = new ArrayList<>();
        extraHours.add(extraHour.get());
        Mockito.when(extraHoursRepository.findByDocumentNumber(Mockito.anyInt())).thenReturn(extraHours);

        // WHEN
        List<ExtraHours> result = extraHoursService.findByDocumentNumber(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(extraHoursRepository.findByDocumentNumber(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            List<ExtraHours> result = extraHoursService.findByDocumentNumber(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Mockito.when(employeeService.findByDocumentNumber(Mockito.any())).thenReturn(new Employee());
        Mockito.when(extraHoursRepository.save(Mockito.any())).thenReturn(new ExtraHours());

        // WHEN
        ExtraHours result = extraHoursService.trackExtraHour(33633264, false);

        // THEN
        Assertions.assertTrue(result != null);
    }
}
