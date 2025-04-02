package com.microservice.food.web.rest;

import com.microservice.food.service.CategoriaPlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.CategoriaPlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.CategoriaPlatillo}.
 */
@RestController
@RequestMapping("//api")
public class CategoriaPlatilloResource {

    private final Logger log = LoggerFactory.getLogger(CategoriaPlatilloResource.class);

    private static final String ENTITY_NAME = "foodCategoriaPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoriaPlatilloService categoriaPlatilloService;

    public CategoriaPlatilloResource(CategoriaPlatilloService categoriaPlatilloService) {
        this.categoriaPlatilloService = categoriaPlatilloService;
    }

    /**
     * {@code POST  /categoria-platillos} : Create a new categoriaPlatillo.
     *
     * @param categoriaPlatilloDTO the categoriaPlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoriaPlatilloDTO, or with status {@code 400 (Bad Request)} if the categoriaPlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categoria-platillos")
    public ResponseEntity<CategoriaPlatilloDTO> createCategoriaPlatillo(@RequestBody CategoriaPlatilloDTO categoriaPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save CategoriaPlatillo : {}", categoriaPlatilloDTO);
        if (categoriaPlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoriaPlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoriaPlatilloDTO result = categoriaPlatilloService.save(categoriaPlatilloDTO);
        return ResponseEntity.created(new URI("/api/categoria-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categoria-platillos} : Updates an existing categoriaPlatillo.
     *
     * @param categoriaPlatilloDTO the categoriaPlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaPlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the categoriaPlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoriaPlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categoria-platillos")
    public ResponseEntity<CategoriaPlatilloDTO> updateCategoriaPlatillo(@RequestBody CategoriaPlatilloDTO categoriaPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update CategoriaPlatillo : {}", categoriaPlatilloDTO);
        if (categoriaPlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoriaPlatilloDTO result = categoriaPlatilloService.save(categoriaPlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaPlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categoria-platillos} : get all the categoriaPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoriaPlatillos in body.
     */
    @GetMapping("/categoria-platillos")
    public List<CategoriaPlatilloDTO> getAllCategoriaPlatillos() {
        log.debug("REST request to get all CategoriaPlatillos");
        return categoriaPlatilloService.findAll();
    }

    /**
     * {@code GET  /categoria-platillos/:id} : get the "id" categoriaPlatillo.
     *
     * @param id the id of the categoriaPlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoriaPlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categoria-platillos/{id}")
    public ResponseEntity<CategoriaPlatilloDTO> getCategoriaPlatillo(@PathVariable Long id) {
        log.debug("REST request to get CategoriaPlatillo : {}", id);
        Optional<CategoriaPlatilloDTO> categoriaPlatilloDTO = categoriaPlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoriaPlatilloDTO);
    }

    /**
     * {@code DELETE  /categoria-platillos/:id} : delete the "id" categoriaPlatillo.
     *
     * @param id the id of the categoriaPlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categoria-platillos/{id}")
    public ResponseEntity<Void> deleteCategoriaPlatillo(@PathVariable Long id) {
        log.debug("REST request to delete CategoriaPlatillo : {}", id);
        categoriaPlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
