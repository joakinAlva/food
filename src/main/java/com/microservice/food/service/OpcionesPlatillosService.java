package com.microservice.food.service;

import com.microservice.food.service.dto.OpcionesPlatillosDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.OpcionesPlatillos}.
 */
public interface OpcionesPlatillosService {

    /**
     * Save a opcionesPlatillos.
     *
     * @param opcionesPlatillosDTO the entity to save.
     * @return the persisted entity.
     */
    OpcionesPlatillosDTO save(OpcionesPlatillosDTO opcionesPlatillosDTO);

    /**
     * Get all the opcionesPlatillos.
     *
     * @return the list of entities.
     */
    List<OpcionesPlatillosDTO> findAll();


    /**
     * Get the "id" opcionesPlatillos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OpcionesPlatillosDTO> findOne(Long id);

    /**
     * Delete the "id" opcionesPlatillos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
