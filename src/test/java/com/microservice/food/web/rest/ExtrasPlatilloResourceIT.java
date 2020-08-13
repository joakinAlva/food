package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.ExtrasPlatillo;
import com.microservice.food.repository.ExtrasPlatilloRepository;
import com.microservice.food.service.ExtrasPlatilloService;
import com.microservice.food.service.dto.ExtrasPlatilloDTO;
import com.microservice.food.service.mapper.ExtrasPlatilloMapper;

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
 * Integration tests for the {@link ExtrasPlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExtrasPlatilloResourceIT {

    @Autowired
    private ExtrasPlatilloRepository extrasPlatilloRepository;

    @Autowired
    private ExtrasPlatilloMapper extrasPlatilloMapper;

    @Autowired
    private ExtrasPlatilloService extrasPlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExtrasPlatilloMockMvc;

    private ExtrasPlatillo extrasPlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtrasPlatillo createEntity(EntityManager em) {
        ExtrasPlatillo extrasPlatillo = new ExtrasPlatillo();
        return extrasPlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtrasPlatillo createUpdatedEntity(EntityManager em) {
        ExtrasPlatillo extrasPlatillo = new ExtrasPlatillo();
        return extrasPlatillo;
    }

    @BeforeEach
    public void initTest() {
        extrasPlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtrasPlatillo() throws Exception {
        int databaseSizeBeforeCreate = extrasPlatilloRepository.findAll().size();
        // Create the ExtrasPlatillo
        ExtrasPlatilloDTO extrasPlatilloDTO = extrasPlatilloMapper.toDto(extrasPlatillo);
        restExtrasPlatilloMockMvc.perform(post("/api/extras-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extrasPlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the ExtrasPlatillo in the database
        List<ExtrasPlatillo> extrasPlatilloList = extrasPlatilloRepository.findAll();
        assertThat(extrasPlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        ExtrasPlatillo testExtrasPlatillo = extrasPlatilloList.get(extrasPlatilloList.size() - 1);
    }

    @Test
    @Transactional
    public void createExtrasPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extrasPlatilloRepository.findAll().size();

        // Create the ExtrasPlatillo with an existing ID
        extrasPlatillo.setId(1L);
        ExtrasPlatilloDTO extrasPlatilloDTO = extrasPlatilloMapper.toDto(extrasPlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtrasPlatilloMockMvc.perform(post("/api/extras-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extrasPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExtrasPlatillo in the database
        List<ExtrasPlatillo> extrasPlatilloList = extrasPlatilloRepository.findAll();
        assertThat(extrasPlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllExtrasPlatillos() throws Exception {
        // Initialize the database
        extrasPlatilloRepository.saveAndFlush(extrasPlatillo);

        // Get all the extrasPlatilloList
        restExtrasPlatilloMockMvc.perform(get("/api/extras-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extrasPlatillo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getExtrasPlatillo() throws Exception {
        // Initialize the database
        extrasPlatilloRepository.saveAndFlush(extrasPlatillo);

        // Get the extrasPlatillo
        restExtrasPlatilloMockMvc.perform(get("/api/extras-platillos/{id}", extrasPlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(extrasPlatillo.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingExtrasPlatillo() throws Exception {
        // Get the extrasPlatillo
        restExtrasPlatilloMockMvc.perform(get("/api/extras-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtrasPlatillo() throws Exception {
        // Initialize the database
        extrasPlatilloRepository.saveAndFlush(extrasPlatillo);

        int databaseSizeBeforeUpdate = extrasPlatilloRepository.findAll().size();

        // Update the extrasPlatillo
        ExtrasPlatillo updatedExtrasPlatillo = extrasPlatilloRepository.findById(extrasPlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedExtrasPlatillo are not directly saved in db
        em.detach(updatedExtrasPlatillo);
        ExtrasPlatilloDTO extrasPlatilloDTO = extrasPlatilloMapper.toDto(updatedExtrasPlatillo);

        restExtrasPlatilloMockMvc.perform(put("/api/extras-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extrasPlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the ExtrasPlatillo in the database
        List<ExtrasPlatillo> extrasPlatilloList = extrasPlatilloRepository.findAll();
        assertThat(extrasPlatilloList).hasSize(databaseSizeBeforeUpdate);
        ExtrasPlatillo testExtrasPlatillo = extrasPlatilloList.get(extrasPlatilloList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingExtrasPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = extrasPlatilloRepository.findAll().size();

        // Create the ExtrasPlatillo
        ExtrasPlatilloDTO extrasPlatilloDTO = extrasPlatilloMapper.toDto(extrasPlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtrasPlatilloMockMvc.perform(put("/api/extras-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extrasPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExtrasPlatillo in the database
        List<ExtrasPlatillo> extrasPlatilloList = extrasPlatilloRepository.findAll();
        assertThat(extrasPlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtrasPlatillo() throws Exception {
        // Initialize the database
        extrasPlatilloRepository.saveAndFlush(extrasPlatillo);

        int databaseSizeBeforeDelete = extrasPlatilloRepository.findAll().size();

        // Delete the extrasPlatillo
        restExtrasPlatilloMockMvc.perform(delete("/api/extras-platillos/{id}", extrasPlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExtrasPlatillo> extrasPlatilloList = extrasPlatilloRepository.findAll();
        assertThat(extrasPlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
