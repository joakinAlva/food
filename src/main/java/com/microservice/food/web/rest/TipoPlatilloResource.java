package com.microservice.food.web.rest;

import com.microservice.food.service.TipoPlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.TipoPlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.TipoPlatillo}.
 */
@RestController
@RequestMapping("/api")
public class TipoPlatilloResource {

    private final Logger log = LoggerFactory.getLogger(TipoPlatilloResource.class);

    private static final String ENTITY_NAME = "foodTipoPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoPlatilloService tipoPlatilloService;

    public TipoPlatilloResource(TipoPlatilloService tipoPlatilloService) {
        this.tipoPlatilloService = tipoPlatilloService;
    }

    /**
     * {@code POST  /tipo-platillos} : Create a new tipoPlatillo.
     *
     * @param tipoPlatilloDTO the tipoPlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoPlatilloDTO, or with status {@code 400 (Bad Request)} if the tipoPlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-platillos")
    public ResponseEntity<TipoPlatilloDTO> createTipoPlatillo(@RequestBody TipoPlatilloDTO tipoPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save TipoPlatillo : {}", tipoPlatilloDTO);
        if (tipoPlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoPlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPlatilloDTO result = tipoPlatilloService.save(tipoPlatilloDTO);
        return ResponseEntity.created(new URI("/api/tipo-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-platillos} : Updates an existing tipoPlatillo.
     *
     * @param tipoPlatilloDTO the tipoPlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoPlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the tipoPlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoPlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-platillos")
    public ResponseEntity<TipoPlatilloDTO> updateTipoPlatillo(@RequestBody TipoPlatilloDTO tipoPlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update TipoPlatillo : {}", tipoPlatilloDTO);
        if (tipoPlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPlatilloDTO result = tipoPlatilloService.save(tipoPlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoPlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-platillos} : get all the tipoPlatillos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoPlatillos in body.
     */
    @GetMapping("/tipo-platillos")
    public List<TipoPlatilloDTO> getAllTipoPlatillos() {
        log.debug("REST request to get all TipoPlatillos");
        return tipoPlatilloService.findAll();
    }

    /**
     * {@code GET  /tipo-platillos/:id} : get the "id" tipoPlatillo.
     *
     * @param id the id of the tipoPlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoPlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-platillos/{id}")
    public ResponseEntity<TipoPlatilloDTO> getTipoPlatillo(@PathVariable Long id) {
        log.debug("REST request to get TipoPlatillo : {}", id);
        Optional<TipoPlatilloDTO> tipoPlatilloDTO = tipoPlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoPlatilloDTO);
    }

    /**
     * {@code DELETE  /tipo-platillos/:id} : delete the "id" tipoPlatillo.
     *
     * @param id the id of the tipoPlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-platillos/{id}")
    public ResponseEntity<Void> deleteTipoPlatillo(@PathVariable Long id) {
        log.debug("REST request to delete TipoPlatillo : {}", id);
        tipoPlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
