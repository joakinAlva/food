package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.OpcionPlatillo;
import com.microservice.food.repository.OpcionPlatilloRepository;
import com.microservice.food.service.OpcionPlatilloService;
import com.microservice.food.service.dto.OpcionPlatilloDTO;
import com.microservice.food.service.mapper.OpcionPlatilloMapper;

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
 * Integration tests for the {@link OpcionPlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OpcionPlatilloResourceIT {

    private static final String DEFAULT_OPCION_PLATILLO = "AAAAAAAAAA";
    private static final String UPDATED_OPCION_PLATILLO = "BBBBBBBBBB";

    @Autowired
    private OpcionPlatilloRepository opcionPlatilloRepository;

    @Autowired
    private OpcionPlatilloMapper opcionPlatilloMapper;

    @Autowired
    private OpcionPlatilloService opcionPlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpcionPlatilloMockMvc;

    private OpcionPlatillo opcionPlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionPlatillo createEntity(EntityManager em) {
        OpcionPlatillo opcionPlatillo = new OpcionPlatillo()
            .opcionPlatillo(DEFAULT_OPCION_PLATILLO);
        return opcionPlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionPlatillo createUpdatedEntity(EntityManager em) {
        OpcionPlatillo opcionPlatillo = new OpcionPlatillo()
            .opcionPlatillo(UPDATED_OPCION_PLATILLO);
        return opcionPlatillo;
    }

    @BeforeEach
    public void initTest() {
        opcionPlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcionPlatillo() throws Exception {
        int databaseSizeBeforeCreate = opcionPlatilloRepository.findAll().size();
        // Create the OpcionPlatillo
        OpcionPlatilloDTO opcionPlatilloDTO = opcionPlatilloMapper.toDto(opcionPlatillo);
        restOpcionPlatilloMockMvc.perform(post("/api/opcion-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionPlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the OpcionPlatillo in the database
        List<OpcionPlatillo> opcionPlatilloList = opcionPlatilloRepository.findAll();
        assertThat(opcionPlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        OpcionPlatillo testOpcionPlatillo = opcionPlatilloList.get(opcionPlatilloList.size() - 1);
        assertThat(testOpcionPlatillo.getOpcionPlatillo()).isEqualTo(DEFAULT_OPCION_PLATILLO);
    }

    @Test
    @Transactional
    public void createOpcionPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcionPlatilloRepository.findAll().size();

        // Create the OpcionPlatillo with an existing ID
        opcionPlatillo.setId(1L);
        OpcionPlatilloDTO opcionPlatilloDTO = opcionPlatilloMapper.toDto(opcionPlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcionPlatilloMockMvc.perform(post("/api/opcion-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionPlatillo in the database
        List<OpcionPlatillo> opcionPlatilloList = opcionPlatilloRepository.findAll();
        assertThat(opcionPlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOpcionPlatillos() throws Exception {
        // Initialize the database
        opcionPlatilloRepository.saveAndFlush(opcionPlatillo);

        // Get all the opcionPlatilloList
        restOpcionPlatilloMockMvc.perform(get("/api/opcion-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcionPlatillo.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcionPlatillo").value(hasItem(DEFAULT_OPCION_PLATILLO)));
    }
    
    @Test
    @Transactional
    public void getOpcionPlatillo() throws Exception {
        // Initialize the database
        opcionPlatilloRepository.saveAndFlush(opcionPlatillo);

        // Get the opcionPlatillo
        restOpcionPlatilloMockMvc.perform(get("/api/opcion-platillos/{id}", opcionPlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opcionPlatillo.getId().intValue()))
            .andExpect(jsonPath("$.opcionPlatillo").value(DEFAULT_OPCION_PLATILLO));
    }
    @Test
    @Transactional
    public void getNonExistingOpcionPlatillo() throws Exception {
        // Get the opcionPlatillo
        restOpcionPlatilloMockMvc.perform(get("/api/opcion-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcionPlatillo() throws Exception {
        // Initialize the database
        opcionPlatilloRepository.saveAndFlush(opcionPlatillo);

        int databaseSizeBeforeUpdate = opcionPlatilloRepository.findAll().size();

        // Update the opcionPlatillo
        OpcionPlatillo updatedOpcionPlatillo = opcionPlatilloRepository.findById(opcionPlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedOpcionPlatillo are not directly saved in db
        em.detach(updatedOpcionPlatillo);
        updatedOpcionPlatillo
            .opcionPlatillo(UPDATED_OPCION_PLATILLO);
        OpcionPlatilloDTO opcionPlatilloDTO = opcionPlatilloMapper.toDto(updatedOpcionPlatillo);

        restOpcionPlatilloMockMvc.perform(put("/api/opcion-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionPlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the OpcionPlatillo in the database
        List<OpcionPlatillo> opcionPlatilloList = opcionPlatilloRepository.findAll();
        assertThat(opcionPlatilloList).hasSize(databaseSizeBeforeUpdate);
        OpcionPlatillo testOpcionPlatillo = opcionPlatilloList.get(opcionPlatilloList.size() - 1);
        assertThat(testOpcionPlatillo.getOpcionPlatillo()).isEqualTo(UPDATED_OPCION_PLATILLO);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcionPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = opcionPlatilloRepository.findAll().size();

        // Create the OpcionPlatillo
        OpcionPlatilloDTO opcionPlatilloDTO = opcionPlatilloMapper.toDto(opcionPlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcionPlatilloMockMvc.perform(put("/api/opcion-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionPlatillo in the database
        List<OpcionPlatillo> opcionPlatilloList = opcionPlatilloRepository.findAll();
        assertThat(opcionPlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcionPlatillo() throws Exception {
        // Initialize the database
        opcionPlatilloRepository.saveAndFlush(opcionPlatillo);

        int databaseSizeBeforeDelete = opcionPlatilloRepository.findAll().size();

        // Delete the opcionPlatillo
        restOpcionPlatilloMockMvc.perform(delete("/api/opcion-platillos/{id}", opcionPlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OpcionPlatillo> opcionPlatilloList = opcionPlatilloRepository.findAll();
        assertThat(opcionPlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
