package com.microservice.food.service;

import com.microservice.food.service.dto.ComplementosPlatilloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.ComplementosPlatillo}.
 */
public interface ComplementosPlatilloService {

    /**
     * Save a complementosPlatillo.
     *
     * @param complementosPlatilloDTO the entity to save.
     * @return the persisted entity.
     */
    ComplementosPlatilloDTO save(ComplementosPlatilloDTO complementosPlatilloDTO);

    /**
     * Get all the complementosPlatillos.
     *
     * @return the list of entities.
     */
    List<ComplementosPlatilloDTO> findAll();


    /**
     * Get the "id" complementosPlatillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComplementosPlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" complementosPlatillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
