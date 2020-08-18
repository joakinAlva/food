package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.OpcionCombo;
import com.microservice.food.repository.OpcionComboRepository;
import com.microservice.food.service.OpcionComboService;
import com.microservice.food.service.dto.OpcionComboDTO;
import com.microservice.food.service.mapper.OpcionComboMapper;

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
 * Integration tests for the {@link OpcionComboResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OpcionComboResourceIT {

    private static final String DEFAULT_OPCION_COMBO = "AAAAAAAAAA";
    private static final String UPDATED_OPCION_COMBO = "BBBBBBBBBB";

    private static final String DEFAULT_CNATIDAD = "AAAAAAAAAA";
    private static final String UPDATED_CNATIDAD = "BBBBBBBBBB";

    @Autowired
    private OpcionComboRepository opcionComboRepository;

    @Autowired
    private OpcionComboMapper opcionComboMapper;

    @Autowired
    private OpcionComboService opcionComboService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpcionComboMockMvc;

    private OpcionCombo opcionCombo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionCombo createEntity(EntityManager em) {
        OpcionCombo opcionCombo = new OpcionCombo()
            .opcionCombo(DEFAULT_OPCION_COMBO)
            .cnatidad(DEFAULT_CNATIDAD);
        return opcionCombo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionCombo createUpdatedEntity(EntityManager em) {
        OpcionCombo opcionCombo = new OpcionCombo()
            .opcionCombo(UPDATED_OPCION_COMBO)
            .cnatidad(UPDATED_CNATIDAD);
        return opcionCombo;
    }

    @BeforeEach
    public void initTest() {
        opcionCombo = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcionCombo() throws Exception {
        int databaseSizeBeforeCreate = opcionComboRepository.findAll().size();
        // Create the OpcionCombo
        OpcionComboDTO opcionComboDTO = opcionComboMapper.toDto(opcionCombo);
        restOpcionComboMockMvc.perform(post("/api/opcion-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionComboDTO)))
            .andExpect(status().isCreated());

        // Validate the OpcionCombo in the database
        List<OpcionCombo> opcionComboList = opcionComboRepository.findAll();
        assertThat(opcionComboList).hasSize(databaseSizeBeforeCreate + 1);
        OpcionCombo testOpcionCombo = opcionComboList.get(opcionComboList.size() - 1);
        assertThat(testOpcionCombo.getOpcionCombo()).isEqualTo(DEFAULT_OPCION_COMBO);
        assertThat(testOpcionCombo.getCnatidad()).isEqualTo(DEFAULT_CNATIDAD);
    }

    @Test
    @Transactional
    public void createOpcionComboWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcionComboRepository.findAll().size();

        // Create the OpcionCombo with an existing ID
        opcionCombo.setId(1L);
        OpcionComboDTO opcionComboDTO = opcionComboMapper.toDto(opcionCombo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcionComboMockMvc.perform(post("/api/opcion-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionComboDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionCombo in the database
        List<OpcionCombo> opcionComboList = opcionComboRepository.findAll();
        assertThat(opcionComboList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOpcionCombos() throws Exception {
        // Initialize the database
        opcionComboRepository.saveAndFlush(opcionCombo);

        // Get all the opcionComboList
        restOpcionComboMockMvc.perform(get("/api/opcion-combos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcionCombo.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcionCombo").value(hasItem(DEFAULT_OPCION_COMBO)))
            .andExpect(jsonPath("$.[*].cnatidad").value(hasItem(DEFAULT_CNATIDAD)));
    }
    
    @Test
    @Transactional
    public void getOpcionCombo() throws Exception {
        // Initialize the database
        opcionComboRepository.saveAndFlush(opcionCombo);

        // Get the opcionCombo
        restOpcionComboMockMvc.perform(get("/api/opcion-combos/{id}", opcionCombo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opcionCombo.getId().intValue()))
            .andExpect(jsonPath("$.opcionCombo").value(DEFAULT_OPCION_COMBO))
            .andExpect(jsonPath("$.cnatidad").value(DEFAULT_CNATIDAD));
    }
    @Test
    @Transactional
    public void getNonExistingOpcionCombo() throws Exception {
        // Get the opcionCombo
        restOpcionComboMockMvc.perform(get("/api/opcion-combos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcionCombo() throws Exception {
        // Initialize the database
        opcionComboRepository.saveAndFlush(opcionCombo);

        int databaseSizeBeforeUpdate = opcionComboRepository.findAll().size();

        // Update the opcionCombo
        OpcionCombo updatedOpcionCombo = opcionComboRepository.findById(opcionCombo.getId()).get();
        // Disconnect from session so that the updates on updatedOpcionCombo are not directly saved in db
        em.detach(updatedOpcionCombo);
        updatedOpcionCombo
            .opcionCombo(UPDATED_OPCION_COMBO)
            .cnatidad(UPDATED_CNATIDAD);
        OpcionComboDTO opcionComboDTO = opcionComboMapper.toDto(updatedOpcionCombo);

        restOpcionComboMockMvc.perform(put("/api/opcion-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionComboDTO)))
            .andExpect(status().isOk());

        // Validate the OpcionCombo in the database
        List<OpcionCombo> opcionComboList = opcionComboRepository.findAll();
        assertThat(opcionComboList).hasSize(databaseSizeBeforeUpdate);
        OpcionCombo testOpcionCombo = opcionComboList.get(opcionComboList.size() - 1);
        assertThat(testOpcionCombo.getOpcionCombo()).isEqualTo(UPDATED_OPCION_COMBO);
        assertThat(testOpcionCombo.getCnatidad()).isEqualTo(UPDATED_CNATIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcionCombo() throws Exception {
        int databaseSizeBeforeUpdate = opcionComboRepository.findAll().size();

        // Create the OpcionCombo
        OpcionComboDTO opcionComboDTO = opcionComboMapper.toDto(opcionCombo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcionComboMockMvc.perform(put("/api/opcion-combos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionComboDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionCombo in the database
        List<OpcionCombo> opcionComboList = opcionComboRepository.findAll();
        assertThat(opcionComboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcionCombo() throws Exception {
        // Initialize the database
        opcionComboRepository.saveAndFlush(opcionCombo);

        int databaseSizeBeforeDelete = opcionComboRepository.findAll().size();

        // Delete the opcionCombo
        restOpcionComboMockMvc.perform(delete("/api/opcion-combos/{id}", opcionCombo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OpcionCombo> opcionComboList = opcionComboRepository.findAll();
        assertThat(opcionComboList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
