package com.microservice.food.service;

import com.microservice.food.service.dto.PlatillosComboDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.PlatillosCombo}.
 */
public interface PlatillosComboService {

    /**
     * Save a platillosCombo.
     *
     * @param platillosComboDTO the entity to save.
     * @return the persisted entity.
     */
    PlatillosComboDTO save(PlatillosComboDTO platillosComboDTO);

    /**
     * Get all the platillosCombos.
     *
     * @return the list of entities.
     */
    List<PlatillosComboDTO> findAll();


    /**
     * Get the "id" platillosCombo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlatillosComboDTO> findOne(Long id);

    /**
     * Delete the "id" platillosCombo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
