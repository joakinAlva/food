package com.microservice.food.web.rest;

import com.microservice.food.service.OpcionComboService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.OpcionComboDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.microservice.food.domain.OpcionCombo}.
 */
@RestController
@RequestMapping("/api")
public class OpcionComboResource {

    private final Logger log = LoggerFactory.getLogger(OpcionComboResource.class);

    private static final String ENTITY_NAME = "foodOpcionCombo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpcionComboService opcionComboService;

    public OpcionComboResource(OpcionComboService opcionComboService) {
        this.opcionComboService = opcionComboService;
    }

    /**
     * {@code POST  /opcion-combos} : Create a new opcionCombo.
     *
     * @param opcionComboDTO the opcionComboDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opcionComboDTO, or with status {@code 400 (Bad Request)} if the opcionCombo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opcion-combos")
    public ResponseEntity<OpcionComboDTO> createOpcionCombo(@RequestBody OpcionComboDTO opcionComboDTO) throws URISyntaxException {
        log.debug("REST request to save OpcionCombo : {}", opcionComboDTO);
        if (opcionComboDTO.getId() != null) {
            throw new BadRequestAlertException("A new opcionCombo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcionComboDTO result = opcionComboService.save(opcionComboDTO);
        return ResponseEntity.created(new URI("/api/opcion-combos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opcion-combos} : Updates an existing opcionCombo.
     *
     * @param opcionComboDTO the opcionComboDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opcionComboDTO,
     * or with status {@code 400 (Bad Request)} if the opcionComboDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opcionComboDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opcion-combos")
    public ResponseEntity<OpcionComboDTO> updateOpcionCombo(@RequestBody OpcionComboDTO opcionComboDTO) throws URISyntaxException {
        log.debug("REST request to update OpcionCombo : {}", opcionComboDTO);
        if (opcionComboDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcionComboDTO result = opcionComboService.save(opcionComboDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opcionComboDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /opcion-combos} : get all the opcionCombos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of opcionCombos in body.
     */
    @GetMapping("/opcion-combos")
    public List<OpcionComboDTO> getAllOpcionCombos() {
        log.debug("REST request to get all OpcionCombos");
        return opcionComboService.findAll();
    }

    /**
     * {@code GET  /opcion-combos/:id} : get the "id" opcionCombo.
     *
     * @param id the id of the opcionComboDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opcionComboDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opcion-combos/{id}")
    public ResponseEntity<OpcionComboDTO> getOpcionCombo(@PathVariable Long id) {
        log.debug("REST request to get OpcionCombo : {}", id);
        Optional<OpcionComboDTO> opcionComboDTO = opcionComboService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcionComboDTO);
    }

    /**
     * {@code DELETE  /opcion-combos/:id} : delete the "id" opcionCombo.
     *
     * @param id the id of the opcionComboDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opcion-combos/{id}")
    public ResponseEntity<Void> deleteOpcionCombo(@PathVariable Long id) {
        log.debug("REST request to delete OpcionCombo : {}", id);
        opcionComboService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
