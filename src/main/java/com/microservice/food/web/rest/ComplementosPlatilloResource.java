package com.microservice.food.web.rest;

import com.microservice.food.service.ComplementosPlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.ComplementosPlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.ComplementosPlatillo}.
 */
@RestController
@RequestMapping("/api")
public class ComplementosPlatilloResource {

    private final Logger log = LoggerFactory.getLogger(ComplementosPlatilloResource.class);

    private static final String ENTITY_NAME = "foodComplementosPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComplementosPlatilloService complementosPlatilloService;

    public ComplementosPlatilloResource(ComplementosPlatilloService complementosPlatilloService) {
        this.complementosPlatilloService = complementosPlatilloService;
    }

    /**
     * {@code POST  /complementos-platillos} : Create a new complementosPlatillo.
     *
     * @param complementosPlatilloDTO the complementosPlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new complementosPlatilloDTO, or with status {@code 400 (Bad Request)} if the complementosPlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/complementos-platillos")
    public ResponseEntity<ComplementosPlatilloDTO> createComplementosPlatillo(@RequestBody ComplementosPlatilloDTO complementosPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save ComplementosPlatillo : {}", complementosPlatilloDTO);
        if (complementosPlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new complementosPlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComplementosPlatilloDTO result = complementosPlatilloService.save(complementosPlatilloDTO);
        return ResponseEntity.created(new URI("/api/complementos-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /complementos-platillos} : Updates an existing complementosPlatillo.
     *
     * @param complementosPlatilloDTO the complementosPlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated complementosPlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the complementosPlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the complementosPlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/complementos-platillos")
    public ResponseEntity<ComplementosPlatilloDTO> updateComplementosPlatillo(@RequestBody ComplementosPlatilloDTO complementosPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update ComplementosPlatillo : {}", complementosPlatilloDTO);
        if (complementosPlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComplementosPlatilloDTO result = complementosPlatilloService.save(complementosPlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, complementosPlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /complementos-platillos} : get all the complementosPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of complementosPlatillos in body.
     */
    @GetMapping("/complementos-platillos")
    public List<ComplementosPlatilloDTO> getAllComplementosPlatillos() {
        log.debug("REST request to get all ComplementosPlatillos");
        return complementosPlatilloService.findAll();
    }

    /**
     * {@code GET  /complementos-platillos/:id} : get the "id" complementosPlatillo.
     *
     * @param id the id of the complementosPlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the complementosPlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/complementos-platillos/{id}")
    public ResponseEntity<ComplementosPlatilloDTO> getComplementosPlatillo(@PathVariable Long id) {
        log.debug("REST request to get ComplementosPlatillo : {}", id);
        Optional<ComplementosPlatilloDTO> complementosPlatilloDTO = complementosPlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(complementosPlatilloDTO);
    }

    /**
     * {@code DELETE  /complementos-platillos/:id} : delete the "id" complementosPlatillo.
     *
     * @param id the id of the complementosPlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/complementos-platillos/{id}")
    public ResponseEntity<Void> deleteComplementosPlatillo(@PathVariable Long id) {
        log.debug("REST request to delete ComplementosPlatillo : {}", id);
        complementosPlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
