package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.Platillo;
import com.microservice.food.repository.PlatilloRepository;
import com.microservice.food.service.PlatilloService;
import com.microservice.food.service.dto.PlatilloDTO;
import com.microservice.food.service.mapper.PlatilloMapper;

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
 * Integration tests for the {@link PlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlatilloResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_FOTO_SRC = "AAAAAAAAAA";
    private static final String UPDATED_FOTO_SRC = "BBBBBBBBBB";

    private static final String DEFAULT_HORARIO = "AAAAAAAAAA";
    private static final String UPDATED_HORARIO = "BBBBBBBBBB";

    private static final Long DEFAULT_PRECIO = 1L;
    private static final Long UPDATED_PRECIO = 2L;

    @Autowired
    private PlatilloRepository platilloRepository;

    @Autowired
    private PlatilloMapper platilloMapper;

    @Autowired
    private PlatilloService platilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlatilloMockMvc;

    private Platillo platillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Platillo createEntity(EntityManager em) {
        Platillo platillo = new Platillo()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .fotoSrc(DEFAULT_FOTO_SRC)
            .horario(DEFAULT_HORARIO)
            .precio(DEFAULT_PRECIO);
        return platillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Platillo createUpdatedEntity(EntityManager em) {
        Platillo platillo = new Platillo()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .fotoSrc(UPDATED_FOTO_SRC)
            .horario(UPDATED_HORARIO)
            .precio(UPDATED_PRECIO);
        return platillo;
    }

    @BeforeEach
    public void initTest() {
        platillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlatillo() throws Exception {
        int databaseSizeBeforeCreate = platilloRepository.findAll().size();
        // Create the Platillo
        PlatilloDTO platilloDTO = platilloMapper.toDto(platillo);
        restPlatilloMockMvc.perform(post("/api/platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platilloDTO)))
            .andExpect(status().isCreated());

        // Validate the Platillo in the database
        List<Platillo> platilloList = platilloRepository.findAll();
        assertThat(platilloList).hasSize(databaseSizeBeforeCreate + 1);
        Platillo testPlatillo = platilloList.get(platilloList.size() - 1);
        assertThat(testPlatillo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPlatillo.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPlatillo.getFotoSrc()).isEqualTo(DEFAULT_FOTO_SRC);
        assertThat(testPlatillo.getHorario()).isEqualTo(DEFAULT_HORARIO);
        assertThat(testPlatillo.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createPlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = platilloRepository.findAll().size();

        // Create the Platillo with an existing ID
        platillo.setId(1L);
        PlatilloDTO platilloDTO = platilloMapper.toDto(platillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlatilloMockMvc.perform(post("/api/platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Platillo in the database
        List<Platillo> platilloList = platilloRepository.findAll();
        assertThat(platilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlatillos() throws Exception {
        // Initialize the database
        platilloRepository.saveAndFlush(platillo);

        // Get all the platilloList
        restPlatilloMockMvc.perform(get("/api/platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(platillo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].fotoSrc").value(hasItem(DEFAULT_FOTO_SRC)))
            .andExpect(jsonPath("$.[*].horario").value(hasItem(DEFAULT_HORARIO)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.intValue())));
    }
    
    @Test
    @Transactional
    public void getPlatillo() throws Exception {
        // Initialize the database
        platilloRepository.saveAndFlush(platillo);

        // Get the platillo
        restPlatilloMockMvc.perform(get("/api/platillos/{id}", platillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(platillo.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.fotoSrc").value(DEFAULT_FOTO_SRC))
            .andExpect(jsonPath("$.horario").value(DEFAULT_HORARIO))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlatillo() throws Exception {
        // Get the platillo
        restPlatilloMockMvc.perform(get("/api/platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlatillo() throws Exception {
        // Initialize the database
        platilloRepository.saveAndFlush(platillo);

        int databaseSizeBeforeUpdate = platilloRepository.findAll().size();

        // Update the platillo
        Platillo updatedPlatillo = platilloRepository.findById(platillo.getId()).get();
        // Disconnect from session so that the updates on updatedPlatillo are not directly saved in db
        em.detach(updatedPlatillo);
        updatedPlatillo
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .fotoSrc(UPDATED_FOTO_SRC)
            .horario(UPDATED_HORARIO)
            .precio(UPDATED_PRECIO);
        PlatilloDTO platilloDTO = platilloMapper.toDto(updatedPlatillo);

        restPlatilloMockMvc.perform(put("/api/platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platilloDTO)))
            .andExpect(status().isOk());

        // Validate the Platillo in the database
        List<Platillo> platilloList = platilloRepository.findAll();
        assertThat(platilloList).hasSize(databaseSizeBeforeUpdate);
        Platillo testPlatillo = platilloList.get(platilloList.size() - 1);
        assertThat(testPlatillo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPlatillo.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPlatillo.getFotoSrc()).isEqualTo(UPDATED_FOTO_SRC);
        assertThat(testPlatillo.getHorario()).isEqualTo(UPDATED_HORARIO);
        assertThat(testPlatillo.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingPlatillo() throws Exception {
        int databaseSizeBeforeUpdate = platilloRepository.findAll().size();

        // Create the Platillo
        PlatilloDTO platilloDTO = platilloMapper.toDto(platillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlatilloMockMvc.perform(put("/api/platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(platilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Platillo in the database
        List<Platillo> platilloList = platilloRepository.findAll();
        assertThat(platilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlatillo() throws Exception {
        // Initialize the database
        platilloRepository.saveAndFlush(platillo);

        int databaseSizeBeforeDelete = platilloRepository.findAll().size();

        // Delete the platillo
        restPlatilloMockMvc.perform(delete("/api/platillos/{id}", platillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Platillo> platilloList = platilloRepository.findAll();
        assertThat(platilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
