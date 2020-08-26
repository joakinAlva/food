package com.microservice.food.repository;

import com.microservice.food.domain.Complemento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Complemento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplementoRepository extends JpaRepository<Complemento, Long> {
}
