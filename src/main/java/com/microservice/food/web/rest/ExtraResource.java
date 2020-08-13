package com.microservice.food.web.rest;

import com.microservice.food.service.ExtraService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.ExtraDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.Extra}.
 */
@RestController
@RequestMapping("/api")
public class ExtraResource {

    private final Logger log = LoggerFactory.getLogger(ExtraResource.class);

    private static final String ENTITY_NAME = "foodExtra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtraService extraService;

    public ExtraResource(ExtraService extraService) {
        this.extraService = extraService;
    }

    /**
     * {@code POST  /extras} : Create a new extra.
     *
     * @param extraDTO the extraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extraDTO, or with status {@code 400 (Bad Request)} if the extra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/extras")
    public ResponseEntity<ExtraDTO> createExtra(@RequestBody ExtraDTO extraDTO) throws URISyntaxException {
        log.debug("REST request to save Extra : {}", extraDTO);
        if (extraDTO.getId() != null) {
            throw new BadRequestAlertException("A new extra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtraDTO result = extraService.save(extraDTO);
        return ResponseEntity.created(new URI("/api/extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /extras} : Updates an existing extra.
     *
     * @param extraDTO the extraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extraDTO,
     * or with status {@code 400 (Bad Request)} if the extraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/extras")
    public ResponseEntity<ExtraDTO> updateExtra(@RequestBody ExtraDTO extraDTO) throws URISyntaxException {
        log.debug("REST request to update Extra : {}", extraDTO);
        if (extraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtraDTO result = extraService.save(extraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, extraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /extras} : get all the extras.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extras in body.
     */
    @GetMapping("/extras")
    public List<ExtraDTO> getAllExtras() {
        log.debug("REST request to get all Extras");
        return extraService.findAll();
    }

    /**
     * {@code GET  /extras/:id} : get the "id" extra.
     *
     * @param id the id of the extraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/extras/{id}")
    public ResponseEntity<ExtraDTO> getExtra(@PathVariable Long id) {
        log.debug("REST request to get Extra : {}", id);
        Optional<ExtraDTO> extraDTO = extraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extraDTO);
    }

    /**
     * {@code DELETE  /extras/:id} : delete the "id" extra.
     *
     * @param id the id of the extraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/extras/{id}")
    public ResponseEntity<Void> deleteExtra(@PathVariable Long id) {
        log.debug("REST request to delete Extra : {}", id);
        extraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
