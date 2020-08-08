package com.microservice.food.service;

import com.microservice.food.service.dto.TipoPlatilloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.TipoPlatillo}.
 */
public interface TipoPlatilloService {

    /**
     * Save a tipoPlatillo.
     *
     * @param tipoPlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    TipoPlatilloDTO save(TipoPlatilloDTO tipoPlatilloDTO);

    /**
     * Get all the tipoPlatillos.
     *
     * @return the list of entities.
     */
    List<TipoPlatilloDTO> findAll();


    /**
     * Get the "id" tipoPlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoPlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" tipoPlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
