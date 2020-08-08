package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.MenuRestaurante;
import com.microservice.food.repository.MenuRestauranteRepository;
import com.microservice.food.service.MenuRestauranteService;
import com.microservice.food.service.dto.MenuRestauranteDTO;
import com.microservice.food.service.mapper.MenuRestauranteMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MenuRestauranteResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MenuRestauranteResourceIT {

    private static final String DEFAULT_MENU_RESTAURANTE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_RESTAURANTE = "BBBBBBBBBB";

    @Autowired
    private MenuRestauranteRepository menuRestauranteRepository;

    @Autowired
    private MenuRestauranteMapper menuRestauranteMapper;

    @Autowired
    private MenuRestauranteService menuRestauranteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuRestauranteMockMvc;

    private MenuRestaurante menuRestaurante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuRestaurante createEntity(EntityManager em) {
        MenuRestaurante menuRestaurante = new MenuRestaurante()
            .menuRestaurante(DEFAULT_MENU_RESTAURANTE);
        return menuRestaurante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuRestaurante createUpdatedEntity(EntityManager em) {
        MenuRestaurante menuRestaurante = new MenuRestaurante()
            .menuRestaurante(UPDATED_MENU_RESTAURANTE);
        return menuRestaurante;
    }

    @BeforeEach
    public void initTest() {
        menuRestaurante = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuRestaurante() throws Exception {
        int databaseSizeBeforeCreate = menuRestauranteRepository.findAll().size();
        // Create the MenuRestaurante
        MenuRestauranteDTO menuRestauranteDTO = menuRestauranteMapper.toDto(menuRestaurante);
        restMenuRestauranteMockMvc.perform(post("/api/menu-restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestauranteDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuRestaurante in the database
        List<MenuRestaurante> menuRestauranteList = menuRestauranteRepository.findAll();
        assertThat(menuRestauranteList).hasSize(databaseSizeBeforeCreate + 1);
        MenuRestaurante testMenuRestaurante = menuRestauranteList.get(menuRestauranteList.size() - 1);
        assertThat(testMenuRestaurante.getMenuRestaurante()).isEqualTo(DEFAULT_MENU_RESTAURANTE);
    }

    @Test
    @Transactional
    public void createMenuRestauranteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuRestauranteRepository.findAll().size();

        // Create the MenuRestaurante with an existing ID
        menuRestaurante.setId(1L);
        MenuRestauranteDTO menuRestauranteDTO = menuRestauranteMapper.toDto(menuRestaurante);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuRestauranteMockMvc.perform(post("/api/menu-restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestauranteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRestaurante in the database
        List<MenuRestaurante> menuRestauranteList = menuRestauranteRepository.findAll();
        assertThat(menuRestauranteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMenuRestaurantes() throws Exception {
        // Initialize the database
        menuRestauranteRepository.saveAndFlush(menuRestaurante);

        // Get all the menuRestauranteList
        restMenuRestauranteMockMvc.perform(get("/api/menu-restaurantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuRestaurante.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuRestaurante").value(hasItem(DEFAULT_MENU_RESTAURANTE)));
    }
    
    @Test
    @Transactional
    public void getMenuRestaurante() throws Exception {
        // Initialize the database
        menuRestauranteRepository.saveAndFlush(menuRestaurante);

        // Get the menuRestaurante
        restMenuRestauranteMockMvc.perform(get("/api/menu-restaurantes/{id}", menuRestaurante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menuRestaurante.getId().intValue()))
            .andExpect(jsonPath("$.menuRestaurante").value(DEFAULT_MENU_RESTAURANTE));
    }
    @Test
    @Transactional
    public void getNonExistingMenuRestaurante() throws Exception {
        // Get the menuRestaurante
        restMenuRestauranteMockMvc.perform(get("/api/menu-restaurantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuRestaurante() throws Exception {
        // Initialize the database
        menuRestauranteRepository.saveAndFlush(menuRestaurante);

        int databaseSizeBeforeUpdate = menuRestauranteRepository.findAll().size();

        // Update the menuRestaurante
        MenuRestaurante updatedMenuRestaurante = menuRestauranteRepository.findById(menuRestaurante.getId()).get();
        // Disconnect from session so that the updates on updatedMenuRestaurante are not directly saved in db
        em.detach(updatedMenuRestaurante);
        updatedMenuRestaurante
            .menuRestaurante(UPDATED_MENU_RESTAURANTE);
        MenuRestauranteDTO menuRestauranteDTO = menuRestauranteMapper.toDto(updatedMenuRestaurante);

        restMenuRestauranteMockMvc.perform(put("/api/menu-restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestauranteDTO)))
            .andExpect(status().isOk());

        // Validate the MenuRestaurante in the database
        List<MenuRestaurante> menuRestauranteList = menuRestauranteRepository.findAll();
        assertThat(menuRestauranteList).hasSize(databaseSizeBeforeUpdate);
        MenuRestaurante testMenuRestaurante = menuRestauranteList.get(menuRestauranteList.size() - 1);
        assertThat(testMenuRestaurante.getMenuRestaurante()).isEqualTo(UPDATED_MENU_RESTAURANTE);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuRestaurante() throws Exception {
        int databaseSizeBeforeUpdate = menuRestauranteRepository.findAll().size();

        // Create the MenuRestaurante
        MenuRestauranteDTO menuRestauranteDTO = menuRestauranteMapper.toDto(menuRestaurante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuRestauranteMockMvc.perform(put("/api/menu-restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestauranteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRestaurante in the database
        List<MenuRestaurante> menuRestauranteList = menuRestauranteRepository.findAll();
        assertThat(menuRestauranteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuRestaurante() throws Exception {
        // Initialize the database
        menuRestauranteRepository.saveAndFlush(menuRestaurante);

        int databaseSizeBeforeDelete = menuRestauranteRepository.findAll().size();

        // Delete the menuRestaurante
        restMenuRestauranteMockMvc.perform(delete("/api/menu-restaurantes/{id}", menuRestaurante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MenuRestaurante> menuRestauranteList = menuRestauranteRepository.findAll();
        assertThat(menuRestauranteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
