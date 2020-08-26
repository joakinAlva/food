package com.microservice.food.service;

import com.microservice.food.service.dto.ComplementoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.Complemento}.
 */
public interface ComplementoService {

    /**
     * Save a complemento.
     *
     * @param complementoDTO the entity to save.
     * @return the persisted entity.
     */
    ComplementoDTO save(ComplementoDTO complementoDTO);

    /**
     * Get all the complementos.
     *
     * @return the list of entities.
     */
    List<ComplementoDTO> findAll();


    /**
     * Get the "id" complemento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComplementoDTO> findOne(Long id);

    /**
     * Delete the "id" complemento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
