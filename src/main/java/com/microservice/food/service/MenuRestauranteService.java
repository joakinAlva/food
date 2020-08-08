package com.microservice.food.service;

import com.microservice.food.service.dto.MenuRestauranteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.MenuRestaurante}.
 */
public interface MenuRestauranteService {

    /**
     * Save a menuRestaurante.
     *
     * @param menuRestauranteDTO the entity to save.
     * @return the persisted entity.
     */
    MenuRestauranteDTO save(MenuRestauranteDTO menuRestauranteDTO);

    /**
     * Get all the menuRestaurantes.
     *
     * @return the list of entities.
     */
    List<MenuRestauranteDTO> findAll();


    /**
     * Get the "id" menuRestaurante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MenuRestauranteDTO> findOne(Long id);

    /**
     * Delete the "id" menuRestaurante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
