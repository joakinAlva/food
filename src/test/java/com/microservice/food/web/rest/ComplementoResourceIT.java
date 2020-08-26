package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.Complemento;
import com.microservice.food.repository.ComplementoRepository;
import com.microservice.food.service.ComplementoService;
import com.microservice.food.service.dto.ComplementoDTO;
import com.microservice.food.service.mapper.ComplementoMapper;

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
 * Integration tests for the {@link ComplementoResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComplementoResourceIT {

    private static final String DEFAULT_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTO = "BBBBBBBBBB";

    @Autowired
    private ComplementoRepository complementoRepository;

    @Autowired
    private ComplementoMapper complementoMapper;

    @Autowired
    private ComplementoService complementoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComplementoMockMvc;

    private Complemento complemento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Complemento createEntity(EntityManager em) {
        Complemento complemento = new Complemento()
            .complemento(DEFAULT_COMPLEMENTO);
        return complemento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Complemento createUpdatedEntity(EntityManager em) {
        Complemento complemento = new Complemento()
            .complemento(UPDATED_COMPLEMENTO);
        return complemento;
    }

    @BeforeEach
    public void initTest() {
        complemento = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplemento() throws Exception {
        int databaseSizeBeforeCreate = complementoRepository.findAll().size();
        // Create the Complemento
        ComplementoDTO complementoDTO = complementoMapper.toDto(complemento);
        restComplementoMockMvc.perform(post("/api/complementos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementoDTO)))
            .andExpect(status().isCreated());

        // Validate the Complemento in the database
        List<Complemento> complementoList = complementoRepository.findAll();
        assertThat(complementoList).hasSize(databaseSizeBeforeCreate + 1);
        Complemento testComplemento = complementoList.get(complementoList.size() - 1);
        assertThat(testComplemento.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void createComplementoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complementoRepository.findAll().size();

        // Create the Complemento with an existing ID
        complemento.setId(1L);
        ComplementoDTO complementoDTO = complementoMapper.toDto(complemento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplementoMockMvc.perform(post("/api/complementos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Complemento in the database
        List<Complemento> complementoList = complementoRepository.findAll();
        assertThat(complementoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllComplementos() throws Exception {
        // Initialize the database
        complementoRepository.saveAndFlush(complemento);

        // Get all the complementoList
        restComplementoMockMvc.perform(get("/api/complementos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complemento.getId().intValue())))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)));
    }
    
    @Test
    @Transactional
    public void getComplemento() throws Exception {
        // Initialize the database
        complementoRepository.saveAndFlush(complemento);

        // Get the complemento
        restComplementoMockMvc.perform(get("/api/complementos/{id}", complemento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(complemento.getId().intValue()))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO));
    }
    @Test
    @Transactional
    public void getNonExistingComplemento() throws Exception {
        // Get the complemento
        restComplementoMockMvc.perform(get("/api/complementos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplemento() throws Exception {
        // Initialize the database
        complementoRepository.saveAndFlush(complemento);

        int databaseSizeBeforeUpdate = complementoRepository.findAll().size();

        // Update the complemento
        Complemento updatedComplemento = complementoRepository.findById(complemento.getId()).get();
        // Disconnect from session so that the updates on updatedComplemento are not directly saved in db
        em.detach(updatedComplemento);
        updatedComplemento
            .complemento(UPDATED_COMPLEMENTO);
        ComplementoDTO complementoDTO = complementoMapper.toDto(updatedComplemento);

        restComplementoMockMvc.perform(put("/api/complementos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementoDTO)))
            .andExpect(status().isOk());

        // Validate the Complemento in the database
        List<Complemento> complementoList = complementoRepository.findAll();
        assertThat(complementoList).hasSize(databaseSizeBeforeUpdate);
        Complemento testComplemento = complementoList.get(complementoList.size() - 1);
        assertThat(testComplemento.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingComplemento() throws Exception {
        int databaseSizeBeforeUpdate = complementoRepository.findAll().size();

        // Create the Complemento
        ComplementoDTO complementoDTO = complementoMapper.toDto(complemento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplementoMockMvc.perform(put("/api/complementos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(complementoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Complemento in the database
        List<Complemento> complementoList = complementoRepository.findAll();
        assertThat(complementoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComplemento() throws Exception {
        // Initialize the database
        complementoRepository.saveAndFlush(complemento);

        int databaseSizeBeforeDelete = complementoRepository.findAll().size();

        // Delete the complemento
        restComplementoMockMvc.perform(delete("/api/complementos/{id}", complemento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Complemento> complementoList = complementoRepository.findAll();
        assertThat(complementoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
