package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.ComplementosPlatillo;
import com.microservice.food.repository.ComplementosPlatilloRepository;
import com.microservice.food.service.ComplementosPlatilloService;
import com.microservice.food.service.dto.ComplementosPlatilloDTO;
import com.microservice.food.service.mapper.ComplementosPlatilloMapper;

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
 * Integration tests for the {@link ComplementosPlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComplementosPlatilloResourceIT {

    @Autowired
    private ComplementosPlatilloRepository complementosPlatilloRepository;

    @Autowired
    private ComplementosPlatilloMapper complementosPlatilloMapper;

    @Autowired
    private ComplementosPlatilloService complementosPlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComplementosPlatilloMockMvc;

    private ComplementosPlatillo complementosPlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComplementosPlatillo createEntity(EntityManager em) {
        ComplementosPlatillo complementosPlatillo = new ComplementosPlatillo();
        return complementosPlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComplementosPlatillo createUpdatedEntity(EntityManager em) {
        ComplementosPlatillo complementosPlatillo = new ComplementosPlatillo();
        return complementosPlatillo;
    }

    @BeforeEach
    public void initTest() {
        complementosPlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplementosPlatillo() throws Exception {
        int databaseSizeBeforeCreate = complementosPlatilloRepository.findAll().size();
        // Create the ComplementosPlatillo
        ComplementosPlatilloDTO complementosPlatilloDTO = complementosPlatilloMapper.toDto(complementosPlatillo);
        restComplementosPlatilloMockMvc.perform(post("/api/complementos-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementosPlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the ComplementosPlatillo in the database
        List<ComplementosPlatillo> complementosPlatilloList = complementosPlatilloRepository.findAll();
        assertThat(complementosPlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        ComplementosPlatillo testComplementosPlatillo = complementosPlatilloList.get(complementosPlatilloList.size() - 1);
    }

    @Test
    @Transactional
    public void createComplementosPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complementosPlatilloRepository.findAll().size();

        // Create the ComplementosPlatillo with an existing ID
        complementosPlatillo.setId(1L);
        ComplementosPlatilloDTO complementosPlatilloDTO = complementosPlatilloMapper.toDto(complementosPlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplementosPlatilloMockMvc.perform(post("/api/complementos-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementosPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComplementosPlatillo in the database
        List<ComplementosPlatillo> complementosPlatilloList = complementosPlatilloRepository.findAll();
        assertThat(complementosPlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllComplementosPlatillos() throws Exception {
        // Initialize the database
        complementosPlatilloRepository.saveAndFlush(complementosPlatillo);

        // Get all the complementosPlatilloList
        restComplementosPlatilloMockMvc.perform(get("/api/complementos-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complementosPlatillo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getComplementosPlatillo() throws Exception {
        // Initialize the database
        complementosPlatilloRepository.saveAndFlush(complementosPlatillo);

        // Get the complementosPlatillo
        restComplementosPlatilloMockMvc.perform(get("/api/complementos-platillos/{id}", complementosPlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(complementosPlatillo.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingComplementosPlatillo() throws Exception {
        // Get the complementosPlatillo
        restComplementosPlatilloMockMvc.perform(get("/api/complementos-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplementosPlatillo() throws Exception {
        // Initialize the database
        complementosPlatilloRepository.saveAndFlush(complementosPlatillo);

        int databaseSizeBeforeUpdate = complementosPlatilloRepository.findAll().size();

        // Update the complementosPlatillo
        ComplementosPlatillo updatedComplementosPlatillo = complementosPlatilloRepository.findById(complementosPlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedComplementosPlatillo are not directly saved in db
        em.detach(updatedComplementosPlatillo);
        ComplementosPlatilloDTO complementosPlatilloDTO = complementosPlatilloMapper.toDto(updatedComplementosPlatillo);

        restComplementosPlatilloMockMvc.perform(put("/api/complementos-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementosPlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the ComplementosPlatillo in the database
        List<ComplementosPlatillo> complementosPlatilloList = complementosPlatilloRepository.findAll();
        assertThat(complementosPlatilloList).hasSize(databaseSizeBeforeUpdate);
        ComplementosPlatillo testComplementosPlatillo = complementosPlatilloList.get(complementosPlatilloList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingComplementosPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = complementosPlatilloRepository.findAll().size();

        // Create the ComplementosPlatillo
        ComplementosPlatilloDTO complementosPlatilloDTO = complementosPlatilloMapper.toDto(complementosPlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplementosPlatilloMockMvc.perform(put("/api/complementos-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementosPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComplementosPlatillo in the database
        List<ComplementosPlatillo> complementosPlatilloList = complementosPlatilloRepository.findAll();
        assertThat(complementosPlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComplementosPlatillo() throws Exception {
        // Initialize the database
        complementosPlatilloRepository.saveAndFlush(complementosPlatillo);

        int databaseSizeBeforeDelete = complementosPlatilloRepository.findAll().size();

        // Delete the complementosPlatillo
        restComplementosPlatilloMockMvc.perform(delete("/api/complementos-platillos/{id}", complementosPlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ComplementosPlatillo> complementosPlatilloList = complementosPlatilloRepository.findAll();
        assertThat(complementosPlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
