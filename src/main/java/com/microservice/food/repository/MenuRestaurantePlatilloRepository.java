package com.microservice.food.repository;

import com.microservice.food.domain.MenuRestaurantePlatillo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MenuRestaurantePlatillo entity.
 */
@Repository
public interface MenuRestaurantePlatilloRepository extends JpaRepository<MenuRestaurantePlatillo, Long> {

    @Query(value = "select distinct menuRestaurantePlatillo from MenuRestaurantePlatillo menuRestaurantePlatillo left join fetch menuRestaurantePlatillo.platillos",
        countQuery = "select count(distinct menuRestaurantePlatillo) from MenuRestaurantePlatillo menuRestaurantePlatillo")
    Page<MenuRestaurantePlatillo> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct menuRestaurantePlatillo from MenuRestaurantePlatillo menuRestaurantePlatillo left join fetch menuRestaurantePlatillo.platillos")
    List<MenuRestaurantePlatillo> findAllWithEagerRelationships();

    @Query("select menuRestaurantePlatillo from MenuRestaurantePlatillo menuRestaurantePlatillo left join fetch menuRestaurantePlatillo.platillos where menuRestaurantePlatillo.id =:id")
    Optional<MenuRestaurantePlatillo> findOneWithEagerRelationships(@Param("id") Long id);
}
