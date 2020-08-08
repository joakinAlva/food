package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.CategoriaPlatillo;
import com.microservice.food.repository.CategoriaPlatilloRepository;
import com.microservice.food.service.CategoriaPlatilloService;
import com.microservice.food.service.dto.CategoriaPlatilloDTO;
import com.microservice.food.service.mapper.CategoriaPlatilloMapper;

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
 * Integration tests for the {@link CategoriaPlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoriaPlatilloResourceIT {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEN_SRC = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN_SRC = "BBBBBBBBBB";

    @Autowired
    private CategoriaPlatilloRepository categoriaPlatilloRepository;

    @Autowired
    private CategoriaPlatilloMapper categoriaPlatilloMapper;

    @Autowired
    private CategoriaPlatilloService categoriaPlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoriaPlatilloMockMvc;

    private CategoriaPlatillo categoriaPlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaPlatillo createEntity(EntityManager em) {
        CategoriaPlatillo categoriaPlatillo = new CategoriaPlatillo()
            .categoria(DEFAULT_CATEGORIA)
            .imagenSrc(DEFAULT_IMAGEN_SRC);
        return categoriaPlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaPlatillo createUpdatedEntity(EntityManager em) {
        CategoriaPlatillo categoriaPlatillo = new CategoriaPlatillo()
            .categoria(UPDATED_CATEGORIA)
            .imagenSrc(UPDATED_IMAGEN_SRC);
        return categoriaPlatillo;
    }

    @BeforeEach
    public void initTest() {
        categoriaPlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoriaPlatillo() throws Exception {
        int databaseSizeBeforeCreate = categoriaPlatilloRepository.findAll().size();
        // Create the CategoriaPlatillo
        CategoriaPlatilloDTO categoriaPlatilloDTO = categoriaPlatilloMapper.toDto(categoriaPlatillo);
        restCategoriaPlatilloMockMvc.perform(post("/api/categoria-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaPlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoriaPlatillo in the database
        List<CategoriaPlatillo> categoriaPlatilloList = categoriaPlatilloRepository.findAll();
        assertThat(categoriaPlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        CategoriaPlatillo testCategoriaPlatillo = categoriaPlatilloList.get(categoriaPlatilloList.size() - 1);
        assertThat(testCategoriaPlatillo.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testCategoriaPlatillo.getImagenSrc()).isEqualTo(DEFAULT_IMAGEN_SRC);
    }

    @Test
    @Transactional
    public void createCategoriaPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriaPlatilloRepository.findAll().size();

        // Create the CategoriaPlatillo with an existing ID
        categoriaPlatillo.setId(1L);
        CategoriaPlatilloDTO categoriaPlatilloDTO = categoriaPlatilloMapper.toDto(categoriaPlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriaPlatilloMockMvc.perform(post("/api/categoria-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaPlatillo in the database
        List<CategoriaPlatillo> categoriaPlatilloList = categoriaPlatilloRepository.findAll();
        assertThat(categoriaPlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategoriaPlatillos() throws Exception {
        // Initialize the database
        categoriaPlatilloRepository.saveAndFlush(categoriaPlatillo);

        // Get all the categoriaPlatilloList
        restCategoriaPlatilloMockMvc.perform(get("/api/categoria-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoriaPlatillo.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].imagenSrc").value(hasItem(DEFAULT_IMAGEN_SRC)));
    }
    
    @Test
    @Transactional
    public void getCategoriaPlatillo() throws Exception {
        // Initialize the database
        categoriaPlatilloRepository.saveAndFlush(categoriaPlatillo);

        // Get the categoriaPlatillo
        restCategoriaPlatilloMockMvc.perform(get("/api/categoria-platillos/{id}", categoriaPlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoriaPlatillo.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.imagenSrc").value(DEFAULT_IMAGEN_SRC));
    }
    @Test
    @Transactional
    public void getNonExistingCategoriaPlatillo() throws Exception {
        // Get the categoriaPlatillo
        restCategoriaPlatilloMockMvc.perform(get("/api/categoria-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriaPlatillo() throws Exception {
        // Initialize the database
        categoriaPlatilloRepository.saveAndFlush(categoriaPlatillo);

        int databaseSizeBeforeUpdate = categoriaPlatilloRepository.findAll().size();

        // Update the categoriaPlatillo
        CategoriaPlatillo updatedCategoriaPlatillo = categoriaPlatilloRepository.findById(categoriaPlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedCategoriaPlatillo are not directly saved in db
        em.detach(updatedCategoriaPlatillo);
        updatedCategoriaPlatillo
            .categoria(UPDATED_CATEGORIA)
            .imagenSrc(UPDATED_IMAGEN_SRC);
        CategoriaPlatilloDTO categoriaPlatilloDTO = categoriaPlatilloMapper.toDto(updatedCategoriaPlatillo);

        restCategoriaPlatilloMockMvc.perform(put("/api/categoria-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaPlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the CategoriaPlatillo in the database
        List<CategoriaPlatillo> categoriaPlatilloList = categoriaPlatilloRepository.findAll();
        assertThat(categoriaPlatilloList).hasSize(databaseSizeBeforeUpdate);
        CategoriaPlatillo testCategoriaPlatillo = categoriaPlatilloList.get(categoriaPlatilloList.size() - 1);
        assertThat(testCategoriaPlatillo.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testCategoriaPlatillo.getImagenSrc()).isEqualTo(UPDATED_IMAGEN_SRC);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoriaPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = categoriaPlatilloRepository.findAll().size();

        // Create the CategoriaPlatillo
        CategoriaPlatilloDTO categoriaPlatilloDTO = categoriaPlatilloMapper.toDto(categoriaPlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriaPlatilloMockMvc.perform(put("/api/categoria-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaPlatillo in the database
        List<CategoriaPlatillo> categoriaPlatilloList = categoriaPlatilloRepository.findAll();
        assertThat(categoriaPlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoriaPlatillo() throws Exception {
        // Initialize the database
        categoriaPlatilloRepository.saveAndFlush(categoriaPlatillo);

        int databaseSizeBeforeDelete = categoriaPlatilloRepository.findAll().size();

        // Delete the categoriaPlatillo
        restCategoriaPlatilloMockMvc.perform(delete("/api/categoria-platillos/{id}", categoriaPlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoriaPlatillo> categoriaPlatilloList = categoriaPlatilloRepository.findAll();
        assertThat(categoriaPlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
