package com.microservice.food.web.rest;

import com.microservice.food.service.PlatillosComboService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.PlatillosComboDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.PlatillosCombo}.
 */
@RestController
@RequestMapping("/api")
public class PlatillosComboResource {

    private final Logger log = LoggerFactory.getLogger(PlatillosComboResource.class);

    private static final String ENTITY_NAME = "foodPlatillosCombo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlatillosComboService platillosComboService;

    public PlatillosComboResource(PlatillosComboService platillosComboService) {
        this.platillosComboService = platillosComboService;
    }

    /**
     * {@code POST  /platillos-combos} : Create a new platillosCombo.
     *
     * @param platillosComboDTO the platillosComboDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new platillosComboDTO, or with status {@code 400 (Bad Request)} if the platillosCombo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/platillos-combos")
    public ResponseEntity<PlatillosComboDTO> createPlatillosCombo(@RequestBody PlatillosComboDTO platillosComboDTO) throws URISyntaxException {
        log.debug("REST request to save PlatillosCombo : {}", platillosComboDTO);
        if (platillosComboDTO.getId() != null) {
            throw new BadRequestAlertException("A new platillosCombo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlatillosComboDTO result = platillosComboService.save(platillosComboDTO);
        return ResponseEntity.created(new URI("/api/platillos-combos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /platillos-combos} : Updates an existing platillosCombo.
     *
     * @param platillosComboDTO the platillosComboDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated platillosComboDTO,
     * or with status {@code 400 (Bad Request)} if the platillosComboDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the platillosComboDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/platillos-combos")
    public ResponseEntity<PlatillosComboDTO> updatePlatillosCombo(@RequestBody PlatillosComboDTO platillosComboDTO) throws URISyntaxException {
        log.debug("REST request to update PlatillosCombo : {}", platillosComboDTO);
        if (platillosComboDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlatillosComboDTO result = platillosComboService.save(platillosComboDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, platillosComboDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /platillos-combos} : get all the platillosCombos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of platillosCombos in body.
     */
    @GetMapping("/platillos-combos")
    public List<PlatillosComboDTO> getAllPlatillosCombos() {
        log.debug("REST request to get all PlatillosCombos");
        return platillosComboService.findAll();
    }

    /**
     * {@code GET  /platillos-combos/:id} : get the "id" platillosCombo.
     *
     * @param id the id of the platillosComboDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the platillosComboDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/platillos-combos/{id}")
    public ResponseEntity<PlatillosComboDTO> getPlatillosCombo(@PathVariable Long id) {
        log.debug("REST request to get PlatillosCombo : {}", id);
        Optional<PlatillosComboDTO> platillosComboDTO = platillosComboService.findOne(id);
        return ResponseUtil.wrapOrNotFound(platillosComboDTO);
    }

    /**
     * {@code DELETE  /platillos-combos/:id} : delete the "id" platillosCombo.
     *
     * @param id the id of the platillosComboDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/platillos-combos/{id}")
    public ResponseEntity<Void> deletePlatillosCombo(@PathVariable Long id) {
        log.debug("REST request to delete PlatillosCombo : {}", id);
        platillosComboService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
