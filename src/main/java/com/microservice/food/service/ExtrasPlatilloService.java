package com.microservice.food.service;

import com.microservice.food.service.dto.ExtrasPlatilloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.ExtrasPlatillo}.
 */
public interface ExtrasPlatilloService {

    /**
     * Save a extrasPlatillo.
     *
     * @param extrasPlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    ExtrasPlatilloDTO save(ExtrasPlatilloDTO extrasPlatilloDTO);

    /**
     * Get all the extrasPlatillos.
     *
     * @return the list of entities.
     */
    List<ExtrasPlatilloDTO> findAll();


    /**
     * Get the "id" extrasPlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExtrasPlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" extrasPlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
