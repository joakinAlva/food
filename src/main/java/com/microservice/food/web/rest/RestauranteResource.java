package com.microservice.food.web.rest;

import com.microservice.food.service.RestauranteService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.RestauranteDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.Restaurante}.
 */
@RestController
@RequestMapping("/api")
public class RestauranteResource {

    private final Logger log = LoggerFactory.getLogger(RestauranteResource.class);

    private static final String ENTITY_NAME = "foodRestaurante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestauranteService restauranteService;

    public RestauranteResource(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    /**
     * {@code POST  /restaurantes} : Create a new restaurante.
     *
     * @param restauranteDTO the restauranteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restauranteDTO, or with status {@code 400 (Bad Request)} if the restaurante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurantes")
    public ResponseEntity<RestauranteDTO> createRestaurante(@RequestBody RestauranteDTO restauranteDTO) throws URISyntaxException {
        log.debug("REST request to save Restaurante : {}", restauranteDTO);
        if (restauranteDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestauranteDTO result = restauranteService.save(restauranteDTO);
        return ResponseEntity.created(new URI("/api/restaurantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurantes} : Updates an existing restaurante.
     *
     * @param restauranteDTO the restauranteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restauranteDTO,
     * or with status {@code 400 (Bad Request)} if the restauranteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restauranteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurantes")
    public ResponseEntity<RestauranteDTO> updateRestaurante(@RequestBody RestauranteDTO restauranteDTO) throws URISyntaxException {
        log.debug("REST request to update Restaurante : {}", restauranteDTO);
        if (restauranteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RestauranteDTO result = restauranteService.save(restauranteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, restauranteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /restaurantes} : get all the restaurantes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantes in body.
     */
    @GetMapping("/restaurantes")
    public ResponseEntity<List<RestauranteDTO>> getAllRestaurantes(Pageable pageable) {
        log.debug("REST request to get a page of Restaurantes");
        Page<RestauranteDTO> page = restauranteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurantes/:id} : get the "id" restaurante.
     *
     * @param id the id of the restauranteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restauranteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurantes/{id}")
    public ResponseEntity<RestauranteDTO> getRestaurante(@PathVariable Long id) {
        log.debug("REST request to get Restaurante : {}", id);
        Optional<RestauranteDTO> restauranteDTO = restauranteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restauranteDTO);
    }

    /**
     * {@code DELETE  /restaurantes/:id} : delete the "id" restaurante.
     *
     * @param id the id of the restauranteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurantes/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        log.debug("REST request to delete Restaurante : {}", id);
        restauranteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
