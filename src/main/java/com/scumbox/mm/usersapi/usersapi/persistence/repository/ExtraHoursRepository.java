package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.ExtraHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraHoursRepository extends JpaRepository<ExtraHours, Integer> {
    List<ExtraHours> findByDocumentNumber(Integer documentNumber);
}
