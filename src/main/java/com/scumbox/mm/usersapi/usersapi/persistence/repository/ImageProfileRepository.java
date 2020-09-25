package com.scumbox.mm.usersapi.usersapi.persistence.repository;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.ImageProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageProfileRepository extends JpaRepository<ImageProfile, Integer> {
    Optional<ImageProfile> findByDocumentNumber(Integer documentNumber);
}

