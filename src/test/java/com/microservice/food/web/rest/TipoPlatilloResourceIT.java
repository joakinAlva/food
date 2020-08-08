package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.TipoPlatillo;
import com.microservice.food.repository.TipoPlatilloRepository;
import com.microservice.food.service.TipoPlatilloService;
import com.microservice.food.service.dto.TipoPlatilloDTO;
import com.microservice.food.service.mapper.TipoPlatilloMapper;

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
 * Integration tests for the {@link TipoPlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoPlatilloResourceIT {

    private static final String DEFAULT_TIPO_PLATILLO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PLATILLO = "BBBBBBBBBB";

    @Autowired
    private TipoPlatilloRepository tipoPlatilloRepository;

    @Autowired
    private TipoPlatilloMapper tipoPlatilloMapper;

    @Autowired
    private TipoPlatilloService tipoPlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoPlatilloMockMvc;

    private TipoPlatillo tipoPlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoPlatillo createEntity(EntityManager em) {
        TipoPlatillo tipoPlatillo = new TipoPlatillo()
            .tipoPlatillo(DEFAULT_TIPO_PLATILLO);
        return tipoPlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoPlatillo createUpdatedEntity(EntityManager em) {
        TipoPlatillo tipoPlatillo = new TipoPlatillo()
            .tipoPlatillo(UPDATED_TIPO_PLATILLO);
        return tipoPlatillo;
    }

    @BeforeEach
    public void initTest() {
        tipoPlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoPlatillo() throws Exception {
        int databaseSizeBeforeCreate = tipoPlatilloRepository.findAll().size();
        // Create the TipoPlatillo
        TipoPlatilloDTO tipoPlatilloDTO = tipoPlatilloMapper.toDto(tipoPlatillo);
        restTipoPlatilloMockMvc.perform(post("/api/tipo-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoPlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoPlatillo in the database
        List<TipoPlatillo> tipoPlatilloList = tipoPlatilloRepository.findAll();
        assertThat(tipoPlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        TipoPlatillo testTipoPlatillo = tipoPlatilloList.get(tipoPlatilloList.size() - 1);
        assertThat(testTipoPlatillo.getTipoPlatillo()).isEqualTo(DEFAULT_TIPO_PLATILLO);
    }

    @Test
    @Transactional
    public void createTipoPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoPlatilloRepository.findAll().size();

        // Create the TipoPlatillo with an existing ID
        tipoPlatillo.setId(1L);
        TipoPlatilloDTO tipoPlatilloDTO = tipoPlatilloMapper.toDto(tipoPlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoPlatilloMockMvc.perform(post("/api/tipo-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPlatillo in the database
        List<TipoPlatillo> tipoPlatilloList = tipoPlatilloRepository.findAll();
        assertThat(tipoPlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoPlatillos() throws Exception {
        // Initialize the database
        tipoPlatilloRepository.saveAndFlush(tipoPlatillo);

        // Get all the tipoPlatilloList
        restTipoPlatilloMockMvc.perform(get("/api/tipo-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPlatillo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPlatillo").value(hasItem(DEFAULT_TIPO_PLATILLO)));
    }
    
    @Test
    @Transactional
    public void getTipoPlatillo() throws Exception {
        // Initialize the database
        tipoPlatilloRepository.saveAndFlush(tipoPlatillo);

        // Get the tipoPlatillo
        restTipoPlatilloMockMvc.perform(get("/api/tipo-platillos/{id}", tipoPlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoPlatillo.getId().intValue()))
            .andExpect(jsonPath("$.tipoPlatillo").value(DEFAULT_TIPO_PLATILLO));
    }
    @Test
    @Transactional
    public void getNonExistingTipoPlatillo() throws Exception {
        // Get the tipoPlatillo
        restTipoPlatilloMockMvc.perform(get("/api/tipo-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoPlatillo() throws Exception {
        // Initialize the database
        tipoPlatilloRepository.saveAndFlush(tipoPlatillo);

        int databaseSizeBeforeUpdate = tipoPlatilloRepository.findAll().size();

        // Update the tipoPlatillo
        TipoPlatillo updatedTipoPlatillo = tipoPlatilloRepository.findById(tipoPlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedTipoPlatillo are not directly saved in db
        em.detach(updatedTipoPlatillo);
        updatedTipoPlatillo
            .tipoPlatillo(UPDATED_TIPO_PLATILLO);
        TipoPlatilloDTO tipoPlatilloDTO = tipoPlatilloMapper.toDto(updatedTipoPlatillo);

        restTipoPlatilloMockMvc.perform(put("/api/tipo-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoPlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the TipoPlatillo in the database
        List<TipoPlatillo> tipoPlatilloList = tipoPlatilloRepository.findAll();
        assertThat(tipoPlatilloList).hasSize(databaseSizeBeforeUpdate);
        TipoPlatillo testTipoPlatillo = tipoPlatilloList.get(tipoPlatilloList.size() - 1);
        assertThat(testTipoPlatillo.getTipoPlatillo()).isEqualTo(UPDATED_TIPO_PLATILLO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = tipoPlatilloRepository.findAll().size();

        // Create the TipoPlatillo
        TipoPlatilloDTO tipoPlatilloDTO = tipoPlatilloMapper.toDto(tipoPlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoPlatilloMockMvc.perform(put("/api/tipo-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoPlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPlatillo in the database
        List<TipoPlatillo> tipoPlatilloList = tipoPlatilloRepository.findAll();
        assertThat(tipoPlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoPlatillo() throws Exception {
        // Initialize the database
        tipoPlatilloRepository.saveAndFlush(tipoPlatillo);

        int databaseSizeBeforeDelete = tipoPlatilloRepository.findAll().size();

        // Delete the tipoPlatillo
        restTipoPlatilloMockMvc.perform(delete("/api/tipo-platillos/{id}", tipoPlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoPlatillo> tipoPlatilloList = tipoPlatilloRepository.findAll();
        assertThat(tipoPlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
