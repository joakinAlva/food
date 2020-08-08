package com.microservice.food.service;

import com.microservice.food.service.dto.CategoriaPlatilloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.CategoriaPlatillo}.
 */
public interface CategoriaPlatilloService {

    /**
     * Save a categoriaPlatillo.
     *
     * @param categoriaPlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    CategoriaPlatilloDTO save(CategoriaPlatilloDTO categoriaPlatilloDTO);

    /**
     * Get all the categoriaPlatillos.
     *
     * @return the list of entities.
     */
    List<CategoriaPlatilloDTO> findAll();


    /**
     * Get the "id" categoriaPlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoriaPlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" categoriaPlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
