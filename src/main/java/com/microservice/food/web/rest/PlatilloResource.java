package com.microservice.food.web.rest;

import com.microservice.food.service.PlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.PlatilloDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.microservice.food.domain.Platillo}.
 */
@RestController
@RequestMapping("/api")
public class PlatilloResource {

    private final Logger log = LoggerFactory.getLogger(PlatilloResource.class);

    private static final String ENTITY_NAME = "foodPlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlatilloService platilloService;

    public PlatilloResource(PlatilloService platilloService) {
        this.platilloService = platilloService;
    }

    /**
     * {@code POST  /platillos} : Create a new platillo.
     *
     * @param platilloDTO the platilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new platilloDTO, or with status {@code 400 (Bad Request)} if the platillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/platillos")
    public ResponseEntity<PlatilloDTO> createPlatillo(@RequestBody PlatilloDTO platilloDTO) throws URISyntaxException {
        log.debug("REST request to save Platillo : {}", platilloDTO);
        if (platilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new platillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlatilloDTO result = platilloService.save(platilloDTO);
        return ResponseEntity.created(new URI("/api/platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /platillos} : Updates an existing platillo.
     *
     * @param platilloDTO the platilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated platilloDTO,
     * or with status {@code 400 (Bad Request)} if the platilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the platilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/platillos")
    public ResponseEntity<PlatilloDTO> updatePlatillo(@RequestBody PlatilloDTO platilloDTO) throws URISyntaxException {
        log.debug("REST request to update Platillo : {}", platilloDTO);
        if (platilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlatilloDTO result = platilloService.save(platilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, platilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /platillos} : get all the platillos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of platillos in body.
     */
    @GetMapping("/platillos")
    public ResponseEntity<List<PlatilloDTO>> getAllPlatillos(Pageable pageable) {
        log.debug("REST request to get a page of Platillos");
        Page<PlatilloDTO> page = platilloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /platillos/:id} : get the "id" platillo.
     *
     * @param id the id of the platilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the platilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/platillos/{id}")
    public ResponseEntity<PlatilloDTO> getPlatillo(@PathVariable Long id) {
        log.debug("REST request to get Platillo : {}", id);
        Optional<PlatilloDTO> platilloDTO = platilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(platilloDTO);
    }

    /**
     * {@code DELETE  /platillos/:id} : delete the "id" platillo.
     *
     * @param id the id of the platilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/platillos/{id}")
    public ResponseEntity<Void> deletePlatillo(@PathVariable Long id) {
        log.debug("REST request to delete Platillo : {}", id);
        platilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
