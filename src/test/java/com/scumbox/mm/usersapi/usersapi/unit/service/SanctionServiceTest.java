package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Sanction;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.SanctionRepository;
import com.scumbox.mm.usersapi.usersapi.service.SanctionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SanctionServiceTest {
    SanctionRepository sanctionRepository = Mockito.mock(SanctionRepository.class);
    SanctionService sanctionService = new SanctionService(sanctionRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<Sanction> sanction = Optional.of(new Sanction());
        List<Sanction> sanctions = new ArrayList<>();
        sanctions.add(sanction.get());

        // WHEN
        Mockito.when(sanctionRepository.findAll()).thenReturn(sanctions);
        List<Sanction> result = sanctionService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByDni_when_has_value() {
        // GIVEN
        Optional<Sanction> sanction = Optional.of(new Sanction());
        Mockito.when(sanctionRepository.findByDni(Mockito.anyInt())).thenReturn(sanction);

        // WHEN
        Sanction result = sanctionService.findByDni(1);

        // THEN
        Assertions.assertTrue(result != null);
    }

    @Test
    public void test_findByDni_when_hasNot_value() {
        // GIVEN
        Mockito.when(sanctionRepository.findByDni(Mockito.anyInt())).thenThrow(NotFoundException.class);

        // THEN
        try{
            Sanction result = sanctionService.findByDni(1);

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<Sanction> sanction = Optional.of(new Sanction());
        Mockito.when(sanctionRepository.save(Mockito.any())).thenReturn(sanction.get());

        // WHEN
        Sanction result = sanctionService.save(sanction.get());

        // THEN
        Assertions.assertTrue(result != null);
    }
}
