package com.microservice.food.web.rest;

import com.microservice.food.service.ComplementoService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.ComplementoDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.Complemento}.
 */
@RestController
@RequestMapping("/api")
public class ComplementoResource {

    private final Logger log = LoggerFactory.getLogger(ComplementoResource.class);

    private static final String ENTITY_NAME = "foodComplemento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComplementoService complementoService;

    public ComplementoResource(ComplementoService complementoService) {
        this.complementoService = complementoService;
    }

    /**
     * {@code POST  /complementos} : Create a new complemento.
     *
     * @param complementoDTO the complementoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new complementoDTO, or with status {@code 400 (Bad Request)} if the complemento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/complementos")
    public ResponseEntity<ComplementoDTO> createComplemento(@RequestBody ComplementoDTO complementoDTO) throws URISyntaxException {
        log.debug("REST request to save Complemento : {}", complementoDTO);
        if (complementoDTO.getId() != null) {
            throw new BadRequestAlertException("A new complemento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComplementoDTO result = complementoService.save(complementoDTO);
        return ResponseEntity.created(new URI("/api/complementos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /complementos} : Updates an existing complemento.
     *
     * @param complementoDTO the complementoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated complementoDTO,
     * or with status {@code 400 (Bad Request)} if the complementoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the complementoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/complementos")
    public ResponseEntity<ComplementoDTO> updateComplemento(@RequestBody ComplementoDTO complementoDTO) throws URISyntaxException {
        log.debug("REST request to update Complemento : {}", complementoDTO);
        if (complementoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComplementoDTO result = complementoService.save(complementoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, complementoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /complementos} : get all the complementos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of complementos in body.
     */
    @GetMapping("/complementos")
    public List<ComplementoDTO> getAllComplementos() {
        log.debug("REST request to get all Complementos");
        return complementoService.findAll();
    }

    /**
     * {@code GET  /complementos/:id} : get the "id" complemento.
     *
     * @param id the id of the complementoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the complementoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/complementos/{id}")
    public ResponseEntity<ComplementoDTO> getComplemento(@PathVariable Long id) {
        log.debug("REST request to get Complemento : {}", id);
        Optional<ComplementoDTO> complementoDTO = complementoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(complementoDTO);
    }

    /**
     * {@code DELETE  /complementos/:id} : delete the "id" complemento.
     *
     * @param id the id of the complementoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/complementos/{id}")
    public ResponseEntity<Void> deleteComplemento(@PathVariable Long id) {
        log.debug("REST request to delete Complemento : {}", id);
        complementoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
