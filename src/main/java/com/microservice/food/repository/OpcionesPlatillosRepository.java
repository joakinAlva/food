package com.microservice.food.repository;

import com.microservice.food.domain.OpcionesPlatillos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OpcionesPlatillos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcionesPlatillosRepository extends JpaRepository<OpcionesPlatillos, Long> {
}
