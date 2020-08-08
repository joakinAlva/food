package com.microservice.food.service;

import com.microservice.food.service.dto.MenuRestaurantePlatilloDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.MenuRestaurantePlatillo}.
 */
public interface MenuRestaurantePlatilloService {

    /**
     * Save a menuRestaurantePlatillo.
     *
     * @param menuRestaurantePlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    MenuRestaurantePlatilloDTO save(MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO);

    /**
     * Get all the menuRestaurantePlatillos.
     *
     * @return the list of entities.
     */
    List<MenuRestaurantePlatilloDTO> findAll();

    /**
     * Get all the menuRestaurantePlatillos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<MenuRestaurantePlatilloDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" menuRestaurantePlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MenuRestaurantePlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" menuRestaurantePlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
