package com.microservice.food.service;

import com.microservice.food.service.dto.OpcionComboDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.OpcionCombo}.
 */
public interface OpcionComboService {

    /**
     * Save a opcionCombo.
     *
     * @param opcionComboDTO the entity to save.
     * @return the persisted entity.
     */
    OpcionComboDTO save(OpcionComboDTO opcionComboDTO);

    /**
     * Get all the opcionCombos.
     *
     * @return the list of entities.
     */
    List<OpcionComboDTO> findAll();


    /**
     * Get the "id" opcionCombo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OpcionComboDTO> findOne(Long id);

    /**
     * Delete the "id" opcionCombo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
