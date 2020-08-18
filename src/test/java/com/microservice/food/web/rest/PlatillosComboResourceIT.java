package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.PlatillosCombo;
import com.microservice.food.repository.PlatillosComboRepository;
import com.microservice.food.service.PlatillosComboService;
import com.microservice.food.service.dto.PlatillosComboDTO;
import com.microservice.food.service.mapper.PlatillosComboMapper;

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
 * Integration tests for the {@link PlatillosComboResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlatillosComboResourceIT {

    private static final Double DEFAULT_PRECIO = 1D;
    private static final Double UPDATED_PRECIO = 2D;

    @Autowired
    private PlatillosComboRepository platillosComboRepository;

    @Autowired
    private PlatillosComboMapper platillosComboMapper;

    @Autowired
    private PlatillosComboService platillosComboService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlatillosComboMockMvc;

    private PlatillosCombo platillosCombo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlatillosCombo createEntity(EntityManager em) {
        PlatillosCombo platillosCombo = new PlatillosCombo()
            .precio(DEFAULT_PRECIO);
        return platillosCombo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlatillosCombo createUpdatedEntity(EntityManager em) {
        PlatillosCombo platillosCombo = new PlatillosCombo()
            .precio(UPDATED_PRECIO);
        return platillosCombo;
    }

    @BeforeEach
    public void initTest() {
        platillosCombo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlatillosCombo() throws Exception {
        int databaseSizeBeforeCreate = platillosComboRepository.findAll().size();
        // Create the PlatillosCombo
        PlatillosComboDTO platillosComboDTO = platillosComboMapper.toDto(platillosCombo);
        restPlatillosComboMockMvc.perform(post("/api/platillos-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platillosComboDTO)))
            .andExpect(status().isCreated());

        // Validate the PlatillosCombo in the database
        List<PlatillosCombo> platillosComboList = platillosComboRepository.findAll();
        assertThat(platillosComboList).hasSize(databaseSizeBeforeCreate + 1);
        PlatillosCombo testPlatillosCombo = platillosComboList.get(platillosComboList.size() - 1);
        assertThat(testPlatillosCombo.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createPlatillosComboWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = platillosComboRepository.findAll().size();

        // Create the PlatillosCombo with an existing ID
        platillosCombo.setId(1L);
        PlatillosComboDTO platillosComboDTO = platillosComboMapper.toDto(platillosCombo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlatillosComboMockMvc.perform(post("/api/platillos-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platillosComboDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlatillosCombo in the database
        List<PlatillosCombo> platillosComboList = platillosComboRepository.findAll();
        assertThat(platillosComboList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlatillosCombos() throws Exception {
        // Initialize the database
        platillosComboRepository.saveAndFlush(platillosCombo);

        // Get all the platillosComboList
        restPlatillosComboMockMvc.perform(get("/api/platillos-combos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(platillosCombo.getId().intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPlatillosCombo() throws Exception {
        // Initialize the database
        platillosComboRepository.saveAndFlush(platillosCombo);

        // Get the platillosCombo
        restPlatillosComboMockMvc.perform(get("/api/platillos-combos/{id}", platillosCombo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(platillosCombo.getId().intValue()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlatillosCombo() throws Exception {
        // Get the platillosCombo
        restPlatillosComboMockMvc.perform(get("/api/platillos-combos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlatillosCombo() throws Exception {
        // Initialize the database
        platillosComboRepository.saveAndFlush(platillosCombo);

        int databaseSizeBeforeUpdate = platillosComboRepository.findAll().size();

        // Update the platillosCombo
        PlatillosCombo updatedPlatillosCombo = platillosComboRepository.findById(platillosCombo.getId()).get();
        // Disconnect from session so that the updates on updatedPlatillosCombo are not directly saved in db
        em.detach(updatedPlatillosCombo);
        updatedPlatillosCombo
            .precio(UPDATED_PRECIO);
        PlatillosComboDTO platillosComboDTO = platillosComboMapper.toDto(updatedPlatillosCombo);

        restPlatillosComboMockMvc.perform(put("/api/platillos-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platillosComboDTO)))
            .andExpect(status().isOk());

        // Validate the PlatillosCombo in the database
        List<PlatillosCombo> platillosComboList = platillosComboRepository.findAll();
        assertThat(platillosComboList).hasSize(databaseSizeBeforeUpdate);
        PlatillosCombo testPlatillosCombo = platillosComboList.get(platillosComboList.size() - 1);
        assertThat(testPlatillosCombo.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingPlatillosCombo() throws Exception {
        int databaseSizeBeforeUpdate = platillosComboRepository.findAll().size();

        // Create the PlatillosCombo
        PlatillosComboDTO platillosComboDTO = platillosComboMapper.toDto(platillosCombo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlatillosComboMockMvc.perform(put("/api/platillos-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platillosComboDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlatillosCombo in the database
        List<PlatillosCombo> platillosComboList = platillosComboRepository.findAll();
        assertThat(platillosComboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlatillosCombo() throws Exception {
        // Initialize the database
        platillosComboRepository.saveAndFlush(platillosCombo);

        int databaseSizeBeforeDelete = platillosComboRepository.findAll().size();

        // Delete the platillosCombo
        restPlatillosComboMockMvc.perform(delete("/api/platillos-combos/{id}", platillosCombo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlatillosCombo> platillosComboList = platillosComboRepository.findAll();
        assertThat(platillosComboList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
