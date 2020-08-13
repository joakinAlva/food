package com.microservice.food.repository;

import com.microservice.food.domain.ExtrasPlatillo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExtrasPlatillo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtrasPlatilloRepository extends JpaRepository<ExtrasPlatillo, Long> {
}
