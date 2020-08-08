package com.microservice.food.web.rest;

import com.microservice.food.service.MenuRestauranteService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.MenuRestauranteDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.MenuRestaurante}.
 */
@RestController
@RequestMapping("/api")
public class MenuRestauranteResource {

    private final Logger log = LoggerFactory.getLogger(MenuRestauranteResource.class);

    private static final String ENTITY_NAME = "foodMenuRestaurante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuRestauranteService menuRestauranteService;

    public MenuRestauranteResource(MenuRestauranteService menuRestauranteService) {
        this.menuRestauranteService = menuRestauranteService;
    }

    /**
     * {@code POST  /menu-restaurantes} : Create a new menuRestaurante.
     *
     * @param menuRestauranteDTO the menuRestauranteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuRestauranteDTO, or with status {@code 400 (Bad Request)} if the menuRestaurante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menu-restaurantes")
    public ResponseEntity<MenuRestauranteDTO> createMenuRestaurante(@RequestBody MenuRestauranteDTO menuRestauranteDTO) throws URISyntaxException {
        log.debug("REST request to save MenuRestaurante : {}", menuRestauranteDTO);
        if (menuRestauranteDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuRestaurante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuRestauranteDTO result = menuRestauranteService.save(menuRestauranteDTO);
        return ResponseEntity.created(new URI("/api/menu-restaurantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /menu-restaurantes} : Updates an existing menuRestaurante.
     *
     * @param menuRestauranteDTO the menuRestauranteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuRestauranteDTO,
     * or with status {@code 400 (Bad Request)} if the menuRestauranteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuRestauranteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menu-restaurantes")
    public ResponseEntity<MenuRestauranteDTO> updateMenuRestaurante(@RequestBody MenuRestauranteDTO menuRestauranteDTO) throws URISyntaxException {
        log.debug("REST request to update MenuRestaurante : {}", menuRestauranteDTO);
        if (menuRestauranteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuRestauranteDTO result = menuRestauranteService.save(menuRestauranteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menuRestauranteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /menu-restaurantes} : get all the menuRestaurantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuRestaurantes in body.
     */
    @GetMapping("/menu-restaurantes")
    public List<MenuRestauranteDTO> getAllMenuRestaurantes() {
        log.debug("REST request to get all MenuRestaurantes");
        return menuRestauranteService.findAll();
    }

    /**
     * {@code GET  /menu-restaurantes/:id} : get the "id" menuRestaurante.
     *
     * @param id the id of the menuRestauranteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menuRestauranteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/menu-restaurantes/{id}")
    public ResponseEntity<MenuRestauranteDTO> getMenuRestaurante(@PathVariable Long id) {
        log.debug("REST request to get MenuRestaurante : {}", id);
        Optional<MenuRestauranteDTO> menuRestauranteDTO = menuRestauranteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuRestauranteDTO);
    }

    /**
     * {@code DELETE  /menu-restaurantes/:id} : delete the "id" menuRestaurante.
     *
     * @param id the id of the menuRestauranteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menu-restaurantes/{id}")
    public ResponseEntity<Void> deleteMenuRestaurante(@PathVariable Long id) {
        log.debug("REST request to delete MenuRestaurante : {}", id);
        menuRestauranteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
