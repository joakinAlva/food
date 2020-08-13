package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.OpcionesPlatillos;
import com.microservice.food.repository.OpcionesPlatillosRepository;
import com.microservice.food.service.OpcionesPlatillosService;
import com.microservice.food.service.dto.OpcionesPlatillosDTO;
import com.microservice.food.service.mapper.OpcionesPlatillosMapper;

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
 * Integration tests for the {@link OpcionesPlatillosResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OpcionesPlatillosResourceIT {

    private static final String DEFAULT_DESCRIPCION_OPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_OPCION = "BBBBBBBBBB";

    private static final String DEFAULT_PRECIO = "AAAAAAAAAA";
    private static final String UPDATED_PRECIO = "BBBBBBBBBB";

    @Autowired
    private OpcionesPlatillosRepository opcionesPlatillosRepository;

    @Autowired
    private OpcionesPlatillosMapper opcionesPlatillosMapper;

    @Autowired
    private OpcionesPlatillosService opcionesPlatillosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpcionesPlatillosMockMvc;

    private OpcionesPlatillos opcionesPlatillos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionesPlatillos createEntity(EntityManager em) {
        OpcionesPlatillos opcionesPlatillos = new OpcionesPlatillos()
            .descripcionOpcion(DEFAULT_DESCRIPCION_OPCION)
            .precio(DEFAULT_PRECIO);
        return opcionesPlatillos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcionesPlatillos createUpdatedEntity(EntityManager em) {
        OpcionesPlatillos opcionesPlatillos = new OpcionesPlatillos()
            .descripcionOpcion(UPDATED_DESCRIPCION_OPCION)
            .precio(UPDATED_PRECIO);
        return opcionesPlatillos;
    }

    @BeforeEach
    public void initTest() {
        opcionesPlatillos = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpcionesPlatillos() throws Exception {
        int databaseSizeBeforeCreate = opcionesPlatillosRepository.findAll().size();
        // Create the OpcionesPlatillos
        OpcionesPlatillosDTO opcionesPlatillosDTO = opcionesPlatillosMapper.toDto(opcionesPlatillos);
        restOpcionesPlatillosMockMvc.perform(post("/api/opciones-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionesPlatillosDTO)))
            .andExpect(status().isCreated());

        // Validate the OpcionesPlatillos in the database
        List<OpcionesPlatillos> opcionesPlatillosList = opcionesPlatillosRepository.findAll();
        assertThat(opcionesPlatillosList).hasSize(databaseSizeBeforeCreate + 1);
        OpcionesPlatillos testOpcionesPlatillos = opcionesPlatillosList.get(opcionesPlatillosList.size() - 1);
        assertThat(testOpcionesPlatillos.getDescripcionOpcion()).isEqualTo(DEFAULT_DESCRIPCION_OPCION);
        assertThat(testOpcionesPlatillos.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createOpcionesPlatillosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opcionesPlatillosRepository.findAll().size();

        // Create the OpcionesPlatillos with an existing ID
        opcionesPlatillos.setId(1L);
        OpcionesPlatillosDTO opcionesPlatillosDTO = opcionesPlatillosMapper.toDto(opcionesPlatillos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcionesPlatillosMockMvc.perform(post("/api/opciones-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionesPlatillosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionesPlatillos in the database
        List<OpcionesPlatillos> opcionesPlatillosList = opcionesPlatillosRepository.findAll();
        assertThat(opcionesPlatillosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOpcionesPlatillos() throws Exception {
        // Initialize the database
        opcionesPlatillosRepository.saveAndFlush(opcionesPlatillos);

        // Get all the opcionesPlatillosList
        restOpcionesPlatillosMockMvc.perform(get("/api/opciones-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcionesPlatillos.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcionOpcion").value(hasItem(DEFAULT_DESCRIPCION_OPCION)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO)));
    }
    
    @Test
    @Transactional
    public void getOpcionesPlatillos() throws Exception {
        // Initialize the database
        opcionesPlatillosRepository.saveAndFlush(opcionesPlatillos);

        // Get the opcionesPlatillos
        restOpcionesPlatillosMockMvc.perform(get("/api/opciones-platillos/{id}", opcionesPlatillos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opcionesPlatillos.getId().intValue()))
            .andExpect(jsonPath("$.descripcionOpcion").value(DEFAULT_DESCRIPCION_OPCION))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO));
    }
    @Test
    @Transactional
    public void getNonExistingOpcionesPlatillos() throws Exception {
        // Get the opcionesPlatillos
        restOpcionesPlatillosMockMvc.perform(get("/api/opciones-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpcionesPlatillos() throws Exception {
        // Initialize the database
        opcionesPlatillosRepository.saveAndFlush(opcionesPlatillos);

        int databaseSizeBeforeUpdate = opcionesPlatillosRepository.findAll().size();

        // Update the opcionesPlatillos
        OpcionesPlatillos updatedOpcionesPlatillos = opcionesPlatillosRepository.findById(opcionesPlatillos.getId()).get();
        // Disconnect from session so that the updates on updatedOpcionesPlatillos are not directly saved in db
        em.detach(updatedOpcionesPlatillos);
        updatedOpcionesPlatillos
            .descripcionOpcion(UPDATED_DESCRIPCION_OPCION)
            .precio(UPDATED_PRECIO);
        OpcionesPlatillosDTO opcionesPlatillosDTO = opcionesPlatillosMapper.toDto(updatedOpcionesPlatillos);

        restOpcionesPlatillosMockMvc.perform(put("/api/opciones-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionesPlatillosDTO)))
            .andExpect(status().isOk());

        // Validate the OpcionesPlatillos in the database
        List<OpcionesPlatillos> opcionesPlatillosList = opcionesPlatillosRepository.findAll();
        assertThat(opcionesPlatillosList).hasSize(databaseSizeBeforeUpdate);
        OpcionesPlatillos testOpcionesPlatillos = opcionesPlatillosList.get(opcionesPlatillosList.size() - 1);
        assertThat(testOpcionesPlatillos.getDescripcionOpcion()).isEqualTo(UPDATED_DESCRIPCION_OPCION);
        assertThat(testOpcionesPlatillos.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingOpcionesPlatillos() throws Exception {
        int databaseSizeBeforeUpdate = opcionesPlatillosRepository.findAll().size();

        // Create the OpcionesPlatillos
        OpcionesPlatillosDTO opcionesPlatillosDTO = opcionesPlatillosMapper.toDto(opcionesPlatillos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcionesPlatillosMockMvc.perform(put("/api/opciones-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opcionesPlatillosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpcionesPlatillos in the database
        List<OpcionesPlatillos> opcionesPlatillosList = opcionesPlatillosRepository.findAll();
        assertThat(opcionesPlatillosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpcionesPlatillos() throws Exception {
        // Initialize the database
        opcionesPlatillosRepository.saveAndFlush(opcionesPlatillos);

        int databaseSizeBeforeDelete = opcionesPlatillosRepository.findAll().size();

        // Delete the opcionesPlatillos
        restOpcionesPlatillosMockMvc.perform(delete("/api/opciones-platillos/{id}", opcionesPlatillos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OpcionesPlatillos> opcionesPlatillosList = opcionesPlatillosRepository.findAll();
        assertThat(opcionesPlatillosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
