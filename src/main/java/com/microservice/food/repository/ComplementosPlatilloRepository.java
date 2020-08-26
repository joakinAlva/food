package com.microservice.food.repository;

import com.microservice.food.domain.ComplementosPlatillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ComplementosPlatillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplementosPlatilloRepository extends JpaRepository<ComplementosPlatillo, Long> {
}
