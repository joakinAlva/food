package com.microservice.food.repository;

import com.microservice.food.domain.PlatillosCombo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlatillosCombo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlatillosComboRepository extends JpaRepository<PlatillosCombo, Long> {
}
