package com.microservice.food.service;

import com.microservice.food.service.dto.RestauranteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.Restaurante}.
 */
public interface RestauranteService {

    /**
     * Save a restaurante.
     *
     * @param restauranteDTO the entity to save.
     * @return the persisted entity.
     */
    RestauranteDTO save(RestauranteDTO restauranteDTO);

    /**
     * Get all the restaurantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RestauranteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" restaurante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RestauranteDTO> findOne(Long id);

    /**
     * Delete the "id" restaurante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
