package com.microservice.food.web.rest;

import com.microservice.food.service.MenuRestaurantePlatilloService;
import com.microservice.food.web.rest.errors.BadRequestAlertException;
import com.microservice.food.service.dto.MenuRestaurantePlatilloDTO;

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
 * REST controller for managing {@link com.microservice.food.domain.MenuRestaurantePlatillo}.
 */
@RestController
@RequestMapping("/api")
public class MenuRestaurantePlatilloResource {

    private final Logger log = LoggerFactory.getLogger(MenuRestaurantePlatilloResource.class);

    private static final String ENTITY_NAME = "foodMenuRestaurantePlatillo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuRestaurantePlatilloService menuRestaurantePlatilloService;

    public MenuRestaurantePlatilloResource(MenuRestaurantePlatilloService menuRestaurantePlatilloService) {
        this.menuRestaurantePlatilloService = menuRestaurantePlatilloService;
    }

    /**
     * {@code POST  /menu-restaurante-platillos} : Create a new menuRestaurantePlatillo.
     *
     * @param menuRestaurantePlatilloDTO the menuRestaurantePlatilloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuRestaurantePlatilloDTO, or with status {@code 400 (Bad Request)} if the menuRestaurantePlatillo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menu-restaurante-platillos")
    public ResponseEntity<MenuRestaurantePlatilloDTO> createMenuRestaurantePlatillo(@RequestBody MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO) throws URISyntaxException {
        log.debug("REST request to save MenuRestaurantePlatillo : {}", menuRestaurantePlatilloDTO);
        if (menuRestaurantePlatilloDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuRestaurantePlatillo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuRestaurantePlatilloDTO result = menuRestaurantePlatilloService.save(menuRestaurantePlatilloDTO);
        return ResponseEntity.created(new URI("/api/menu-restaurante-platillos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /menu-restaurante-platillos} : Updates an existing menuRestaurantePlatillo.
     *
     * @param menuRestaurantePlatilloDTO the menuRestaurantePlatilloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuRestaurantePlatilloDTO,
     * or with status {@code 400 (Bad Request)} if the menuRestaurantePlatilloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuRestaurantePlatilloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menu-restaurante-platillos")
    public ResponseEntity<MenuRestaurantePlatilloDTO> updateMenuRestaurantePlatillo(@RequestBody MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO) throws URISyntaxException {
        log.debug("REST request to update MenuRestaurantePlatillo : {}", menuRestaurantePlatilloDTO);
        if (menuRestaurantePlatilloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuRestaurantePlatilloDTO result = menuRestaurantePlatilloService.save(menuRestaurantePlatilloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menuRestaurantePlatilloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /menu-restaurante-platillos} : get all the menuRestaurantePlatillos.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuRestaurantePlatillos in body.
     */
    @GetMapping("/menu-restaurante-platillos")
    public List<MenuRestaurantePlatilloDTO> getAllMenuRestaurantePlatillos(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MenuRestaurantePlatillos");
        return menuRestaurantePlatilloService.findAll();
    }

    /**
     * {@code GET  /menu-restaurante-platillos/:id} : get the "id" menuRestaurantePlatillo.
     *
     * @param id the id of the menuRestaurantePlatilloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menuRestaurantePlatilloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/menu-restaurante-platillos/{id}")
    public ResponseEntity<MenuRestaurantePlatilloDTO> getMenuRestaurantePlatillo(@PathVariable Long id) {
        log.debug("REST request to get MenuRestaurantePlatillo : {}", id);
        Optional<MenuRestaurantePlatilloDTO> menuRestaurantePlatilloDTO = menuRestaurantePlatilloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuRestaurantePlatilloDTO);
    }

    /**
     * {@code DELETE  /menu-restaurante-platillos/:id} : delete the "id" menuRestaurantePlatillo.
     *
     * @param id the id of the menuRestaurantePlatilloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menu-restaurante-platillos/{id}")
    public ResponseEntity<Void> deleteMenuRestaurantePlatillo(@PathVariable Long id) {
        log.debug("REST request to delete MenuRestaurantePlatillo : {}", id);
        menuRestaurantePlatilloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
