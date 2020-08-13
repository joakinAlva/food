package com.microservice.food.web.rest;

import com.microservice.food.service.OpcionesPlatillosService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.OpcionesPlatillosDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.OpcionesPlatillos}.
 */
@RestController
@RequestMapping("/api")
public class OpcionesPlatillosResource {

    private final Logger log = LoggerFactory.getLogger(OpcionesPlatillosResource.class);

    private static final String ENTITY_NAME = "foodOpcionesPlatillos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpcionesPlatillosService opcionesPlatillosService;

    public OpcionesPlatillosResource(OpcionesPlatillosService opcionesPlatillosService) {
        this.opcionesPlatillosService = opcionesPlatillosService;
    }

    /**
     * {@code POST  /opciones-platillos} : Create a new opcionesPlatillos.
     *
     * @param opcionesPlatillosDTO the opcionesPlatillosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opcionesPlatillosDTO, or with status {@code 400 (Bad Request)} if the opcionesPlatillos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opciones-platillos")
    public ResponseEntity<OpcionesPlatillosDTO> createOpcionesPlatillos(@RequestBody OpcionesPlatillosDTO opcionesPlatillosDTO) throws URISyntaxException {
        log.debug("REST request to save OpcionesPlatillos : {}", opcionesPlatillosDTO);
        if (opcionesPlatillosDTO.getId() != null) {
            throw new BadRequestAlertException("A new opcionesPlatillos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcionesPlatillosDTO result = opcionesPlatillosService.save(opcionesPlatillosDTO);
        return ResponseEntity.created(new URI("/api/opciones-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opciones-platillos} : Updates an existing opcionesPlatillos.
     *
     * @param opcionesPlatillosDTO the opcionesPlatillosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opcionesPlatillosDTO,
     * or with status {@code 400 (Bad Request)} if the opcionesPlatillosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opcionesPlatillosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opciones-platillos")
    public ResponseEntity<OpcionesPlatillosDTO> updateOpcionesPlatillos(@RequestBody OpcionesPlatillosDTO opcionesPlatillosDTO) throws URISyntaxException {
        log.debug("REST request to update OpcionesPlatillos : {}", opcionesPlatillosDTO);
        if (opcionesPlatillosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcionesPlatillosDTO result = opcionesPlatillosService.save(opcionesPlatillosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opcionesPlatillosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /opciones-platillos} : get all the opcionesPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of opcionesPlatillos in body.
     */
    @GetMapping("/opciones-platillos")
    public List<OpcionesPlatillosDTO> getAllOpcionesPlatillos() {
        log.debug("REST request to get all OpcionesPlatillos");
        return opcionesPlatillosService.findAll();
    }

    /**
     * {@code GET  /opciones-platillos/:id} : get the "id" opcionesPlatillos.
     *
     * @param id the id of the opcionesPlatillosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opcionesPlatillosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opciones-platillos/{id}")
    public ResponseEntity<OpcionesPlatillosDTO> getOpcionesPlatillos(@PathVariable Long id) {
        log.debug("REST request to get OpcionesPlatillos : {}", id);
        Optional<OpcionesPlatillosDTO> opcionesPlatillosDTO = opcionesPlatillosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcionesPlatillosDTO);
    }

    /**
     * {@code DELETE  /opciones-platillos/:id} : delete the "id" opcionesPlatillos.
     *
     * @param id the id of the opcionesPlatillosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opciones-platillos/{id}")
    public ResponseEntity<Void> deleteOpcionesPlatillos(@PathVariable Long id) {
        log.debug("REST request to delete OpcionesPlatillos : {}", id);
        opcionesPlatillosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
