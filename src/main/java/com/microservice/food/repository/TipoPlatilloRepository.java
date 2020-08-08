package com.microservice.food.repository;

import com.microservice.food.domain.TipoPlatillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoPlatillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoPlatilloRepository extends JpaRepository<TipoPlatillo, Long> {
}
