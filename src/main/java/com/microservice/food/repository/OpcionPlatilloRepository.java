package com.microservice.food.repository;

import com.microservice.food.domain.OpcionPlatillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OpcionPlatillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcionPlatilloRepository extends JpaRepository<OpcionPlatillo, Long> {
}
