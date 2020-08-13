package com.microservice.food.web.rest;

import com.microservice.food.service.ExtrasPlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.ExtrasPlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.ExtrasPlatillo}.
 */
@RestController
@RequestMapping("/api")
public class ExtrasPlatilloResource {

    private final Logger log = LoggerFactory.getLogger(ExtrasPlatilloResource.class);

    private static final String ENTITY_NAME = "foodExtrasPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtrasPlatilloService extrasPlatilloService;

    public ExtrasPlatilloResource(ExtrasPlatilloService extrasPlatilloService) {
        this.extrasPlatilloService = extrasPlatilloService;
    }

    /**
     * {@code POST  /extras-platillos} : Create a new extrasPlatillo.
     *
     * @param extrasPlatilloDTO the extrasPlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extrasPlatilloDTO, or with status {@code 400 (Bad Request)} if the extrasPlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/extras-platillos")
    public ResponseEntity<ExtrasPlatilloDTO> createExtrasPlatillo(@RequestBody ExtrasPlatilloDTO extrasPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save ExtrasPlatillo : {}", extrasPlatilloDTO);
        if (extrasPlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new extrasPlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtrasPlatilloDTO result = extrasPlatilloService.save(extrasPlatilloDTO);
        return ResponseEntity.created(new URI("/api/extras-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /extras-platillos} : Updates an existing extrasPlatillo.
     *
     * @param extrasPlatilloDTO the extrasPlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extrasPlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the extrasPlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extrasPlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/extras-platillos")
    public ResponseEntity<ExtrasPlatilloDTO> updateExtrasPlatillo(@RequestBody ExtrasPlatilloDTO extrasPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update ExtrasPlatillo : {}", extrasPlatilloDTO);
        if (extrasPlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtrasPlatilloDTO result = extrasPlatilloService.save(extrasPlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, extrasPlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /extras-platillos} : get all the extrasPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extrasPlatillos in body.
     */
    @GetMapping("/extras-platillos")
    public List<ExtrasPlatilloDTO> getAllExtrasPlatillos() {
        log.debug("REST request to get all ExtrasPlatillos");
        return extrasPlatilloService.findAll();
    }

    /**
     * {@code GET  /extras-platillos/:id} : get the "id" extrasPlatillo.
     *
     * @param id the id of the extrasPlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extrasPlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/extras-platillos/{id}")
    public ResponseEntity<ExtrasPlatilloDTO> getExtrasPlatillo(@PathVariable Long id) {
        log.debug("REST request to get ExtrasPlatillo : {}", id);
        Optional<ExtrasPlatilloDTO> extrasPlatilloDTO = extrasPlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extrasPlatilloDTO);
    }

    /**
     * {@code DELETE  /extras-platillos/:id} : delete the "id" extrasPlatillo.
     *
     * @param id the id of the extrasPlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/extras-platillos/{id}")
    public ResponseEntity<Void> deleteExtrasPlatillo(@PathVariable Long id) {
        log.debug("REST request to delete ExtrasPlatillo : {}", id);
        extrasPlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
