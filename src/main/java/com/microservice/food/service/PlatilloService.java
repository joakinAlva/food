package com.microservice.food.service;

import com.microservice.food.service.dto.PlatilloDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.microservice.food.domain.Platillo}.
 */
public interface PlatilloService {

    /**
     * Save a platillo.
     *
     * @param platilloDTO the entity to save.
     * @return the persisted entity.
     */
    PlatilloDTO save(PlatilloDTO platilloDTO);

    /**
     * Get all the platillos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PlatilloDTO> findAll(Pageable pageable);


    /**
     * Get the "id" platillo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlatilloDTO> findOne(Long id);

    /**
     * Delete the "id" platillo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
