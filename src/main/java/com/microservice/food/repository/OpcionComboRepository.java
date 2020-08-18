package com.microservice.food.repository;

import com.microservice.food.domain.OpcionCombo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OpcionCombo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcionComboRepository extends JpaRepository<OpcionCombo, Long> {
}
