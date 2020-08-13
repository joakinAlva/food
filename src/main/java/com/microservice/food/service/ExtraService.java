package com.microservice.food.service;

import com.microservice.food.service.dto.ExtraDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.Extra}.
 */
public interface ExtraService {

    /**
     * Save a extra.
     *
     * @param extraDTO the entity to save.
     * @return the persisted entity.
     */
    ExtraDTO save(ExtraDTO extraDTO);

    /**
     * Get all the extras.
     *
     * @return the list of entities.
     */
    List<ExtraDTO> findAll();


    /**
     * Get the "id" extra.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExtraDTO> findOne(Long id);

    /**
     * Delete the "id" extra.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
