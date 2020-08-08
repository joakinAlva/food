package com.microservice.food.repository;

import com.microservice.food.domain.Platillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Platillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
}
