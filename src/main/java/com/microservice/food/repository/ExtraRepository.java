package com.microservice.food.repository;

import com.microservice.food.domain.Extra;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Extra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {
}
