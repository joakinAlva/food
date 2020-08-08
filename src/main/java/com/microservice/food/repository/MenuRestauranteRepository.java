package com.microservice.food.repository;

import com.microservice.food.domain.MenuRestaurante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MenuRestaurante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRestauranteRepository extends JpaRepository<MenuRestaurante, Long> {
}
