package com.microservice.food.web.rest;

import com.microservice.food.service.OpcionPlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.OpcionPlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.OpcionPlatillo}.
 */
@RestController
@RequestMapping("/api")
public class OpcionPlatilloResource {

    private final Logger log = LoggerFactory.getLogger(OpcionPlatilloResource.class);

    private static final String ENTITY_NAME = "foodOpcionPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpcionPlatilloService opcionPlatilloService;

    public OpcionPlatilloResource(OpcionPlatilloService opcionPlatilloService) {
        this.opcionPlatilloService = opcionPlatilloService;
    }

    /**
     * {@code POST  /opcion-platillos} : Create a new opcionPlatillo.
     *
     * @param opcionPlatilloDTO the opcionPlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opcionPlatilloDTO, or with status {@code 400 (Bad Request)} if the opcionPlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opcion-platillos")
    public ResponseEntity<OpcionPlatilloDTO> createOpcionPlatillo(@RequestBody OpcionPlatilloDTO opcionPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save OpcionPlatillo : {}", opcionPlatilloDTO);
        if (opcionPlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new opcionPlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcionPlatilloDTO result = opcionPlatilloService.save(opcionPlatilloDTO);
        return ResponseEntity.created(new URI("/api/opcion-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opcion-platillos} : Updates an existing opcionPlatillo.
     *
     * @param opcionPlatilloDTO the opcionPlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opcionPlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the opcionPlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opcionPlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opcion-platillos")
    public ResponseEntity<OpcionPlatilloDTO> updateOpcionPlatillo(@RequestBody OpcionPlatilloDTO opcionPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update OpcionPlatillo : {}", opcionPlatilloDTO);
        if (opcionPlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpcionPlatilloDTO result = opcionPlatilloService.save(opcionPlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opcionPlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /opcion-platillos} : get all the opcionPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of opcionPlatillos in body.
     */
    @GetMapping("/opcion-platillos")
    public List<OpcionPlatilloDTO> getAllOpcionPlatillos() {
        log.debug("REST request to get all OpcionPlatillos");
        return opcionPlatilloService.findAll();
    }

    /**
     * {@code GET  /opcion-platillos/:id} : get the "id" opcionPlatillo.
     *
     * @param id the id of the opcionPlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opcionPlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opcion-platillos/{id}")
    public ResponseEntity<OpcionPlatilloDTO> getOpcionPlatillo(@PathVariable Long id) {
        log.debug("REST request to get OpcionPlatillo : {}", id);
        Optional<OpcionPlatilloDTO> opcionPlatilloDTO = opcionPlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcionPlatilloDTO);
    }

    /**
     * {@code DELETE  /opcion-platillos/:id} : delete the "id" opcionPlatillo.
     *
     * @param id the id of the opcionPlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opcion-platillos/{id}")
    public ResponseEntity<Void> deleteOpcionPlatillo(@PathVariable Long id) {
        log.debug("REST request to delete OpcionPlatillo : {}", id);
        opcionPlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
