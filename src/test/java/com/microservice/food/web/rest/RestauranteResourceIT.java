package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.Restaurante;
import com.microservice.food.repository.RestauranteRepository;
import com.microservice.food.service.RestauranteService;
import com.microservice.food.service.dto.RestauranteDTO;
import com.microservice.food.service.mapper.RestauranteMapper;

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
 * Integration tests for the {@link RestauranteResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RestauranteResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final Long DEFAULT_CALIFICACION = 1L;
    private static final Long UPDATED_CALIFICACION = 2L;

    private static final String DEFAULT_IMAGEN_FONDO_SRC = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN_FONDO_SRC = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEN_PERFIL_SRC = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN_PERFIL_SRC = "BBBBBBBBBB";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteMapper restauranteMapper;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRestauranteMockMvc;

    private Restaurante restaurante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restaurante createEntity(EntityManager em) {
        Restaurante restaurante = new Restaurante()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .direccion(DEFAULT_DIRECCION)
            .calificacion(DEFAULT_CALIFICACION)
            .imagenFondoSrc(DEFAULT_IMAGEN_FONDO_SRC)
            .imagenPerfilSrc(DEFAULT_IMAGEN_PERFIL_SRC);
        return restaurante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restaurante createUpdatedEntity(EntityManager em) {
        Restaurante restaurante = new Restaurante()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .calificacion(UPDATED_CALIFICACION)
            .imagenFondoSrc(UPDATED_IMAGEN_FONDO_SRC)
            .imagenPerfilSrc(UPDATED_IMAGEN_PERFIL_SRC);
        return restaurante;
    }

    @BeforeEach
    public void initTest() {
        restaurante = createEntity(em);
    }

    @Test
    @Transactional
    public void createRestaurante() throws Exception {
        int databaseSizeBeforeCreate = restauranteRepository.findAll().size();
        // Create the Restaurante
        RestauranteDTO restauranteDTO = restauranteMapper.toDto(restaurante);
        restRestauranteMockMvc.perform(post("/api/restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restauranteDTO)))
            .andExpect(status().isCreated());

        // Validate the Restaurante in the database
        List<Restaurante> restauranteList = restauranteRepository.findAll();
        assertThat(restauranteList).hasSize(databaseSizeBeforeCreate + 1);
        Restaurante testRestaurante = restauranteList.get(restauranteList.size() - 1);
        assertThat(testRestaurante.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testRestaurante.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testRestaurante.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testRestaurante.getCalificacion()).isEqualTo(DEFAULT_CALIFICACION);
        assertThat(testRestaurante.getImagenFondoSrc()).isEqualTo(DEFAULT_IMAGEN_FONDO_SRC);
        assertThat(testRestaurante.getImagenPerfilSrc()).isEqualTo(DEFAULT_IMAGEN_PERFIL_SRC);
    }

    @Test
    @Transactional
    public void createRestauranteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = restauranteRepository.findAll().size();

        // Create the Restaurante with an existing ID
        restaurante.setId(1L);
        RestauranteDTO restauranteDTO = restauranteMapper.toDto(restaurante);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRestauranteMockMvc.perform(post("/api/restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restauranteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restaurante in the database
        List<Restaurante> restauranteList = restauranteRepository.findAll();
        assertThat(restauranteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRestaurantes() throws Exception {
        // Initialize the database
        restauranteRepository.saveAndFlush(restaurante);

        // Get all the restauranteList
        restRestauranteMockMvc.perform(get("/api/restaurantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(restaurante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].calificacion").value(hasItem(DEFAULT_CALIFICACION.intValue())))
            .andExpect(jsonPath("$.[*].imagenFondoSrc").value(hasItem(DEFAULT_IMAGEN_FONDO_SRC)))
            .andExpect(jsonPath("$.[*].imagenPerfilSrc").value(hasItem(DEFAULT_IMAGEN_PERFIL_SRC)));
    }
    
    @Test
    @Transactional
    public void getRestaurante() throws Exception {
        // Initialize the database
        restauranteRepository.saveAndFlush(restaurante);

        // Get the restaurante
        restRestauranteMockMvc.perform(get("/api/restaurantes/{id}", restaurante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(restaurante.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.calificacion").value(DEFAULT_CALIFICACION.intValue()))
            .andExpect(jsonPath("$.imagenFondoSrc").value(DEFAULT_IMAGEN_FONDO_SRC))
            .andExpect(jsonPath("$.imagenPerfilSrc").value(DEFAULT_IMAGEN_PERFIL_SRC));
    }
    @Test
    @Transactional
    public void getNonExistingRestaurante() throws Exception {
        // Get the restaurante
        restRestauranteMockMvc.perform(get("/api/restaurantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRestaurante() throws Exception {
        // Initialize the database
        restauranteRepository.saveAndFlush(restaurante);

        int databaseSizeBeforeUpdate = restauranteRepository.findAll().size();

        // Update the restaurante
        Restaurante updatedRestaurante = restauranteRepository.findById(restaurante.getId()).get();
        // Disconnect from session so that the updates on updatedRestaurante are not directly saved in db
        em.detach(updatedRestaurante);
        updatedRestaurante
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .calificacion(UPDATED_CALIFICACION)
            .imagenFondoSrc(UPDATED_IMAGEN_FONDO_SRC)
            .imagenPerfilSrc(UPDATED_IMAGEN_PERFIL_SRC);
        RestauranteDTO restauranteDTO = restauranteMapper.toDto(updatedRestaurante);

        restRestauranteMockMvc.perform(put("/api/restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restauranteDTO)))
            .andExpect(status().isOk());

        // Validate the Restaurante in the database
        List<Restaurante> restauranteList = restauranteRepository.findAll();
        assertThat(restauranteList).hasSize(databaseSizeBeforeUpdate);
        Restaurante testRestaurante = restauranteList.get(restauranteList.size() - 1);
        assertThat(testRestaurante.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testRestaurante.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testRestaurante.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testRestaurante.getCalificacion()).isEqualTo(UPDATED_CALIFICACION);
        assertThat(testRestaurante.getImagenFondoSrc()).isEqualTo(UPDATED_IMAGEN_FONDO_SRC);
        assertThat(testRestaurante.getImagenPerfilSrc()).isEqualTo(UPDATED_IMAGEN_PERFIL_SRC);
    }

    @Test
    @Transactional
    public void updateNonExistingRestaurante() throws Exception {
        int databaseSizeBeforeUpdate = restauranteRepository.findAll().size();

        // Create the Restaurante
        RestauranteDTO restauranteDTO = restauranteMapper.toDto(restaurante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRestauranteMockMvc.perform(put("/api/restaurantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restauranteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restaurante in the database
        List<Restaurante> restauranteList = restauranteRepository.findAll();
        assertThat(restauranteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRestaurante() throws Exception {
        // Initialize the database
        restauranteRepository.saveAndFlush(restaurante);

        int databaseSizeBeforeDelete = restauranteRepository.findAll().size();

        // Delete the restaurante
        restRestauranteMockMvc.perform(delete("/api/restaurantes/{id}", restaurante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Restaurante> restauranteList = restauranteRepository.findAll();
        assertThat(restauranteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
