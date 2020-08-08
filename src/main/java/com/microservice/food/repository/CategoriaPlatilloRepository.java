package com.microservice.food.repository;

import com.microservice.food.domain.CategoriaPlatillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategoriaPlatillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaPlatilloRepository extends JpaRepository<CategoriaPlatillo, Long> {
}
