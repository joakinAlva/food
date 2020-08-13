package com.microservice.food.service;

import com.microservice.food.service.dto.OpcionPlatilloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.OpcionPlatillo}.
 */
public interface OpcionPlatilloService {

    /**
     * Save a opcionPlatillo.
     *
     * @param opcionPlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    OpcionPlatilloDTO save(OpcionPlatilloDTO opcionPlatilloDTO);

    /**
     * Get all the opcionPlatillos.
     *
     * @return the list of entities.
     */
    List<OpcionPlatilloDTO> findAll();


    /**
     * Get the "id" opcionPlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OpcionPlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" opcionPlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
