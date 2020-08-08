package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.MenuRestaurantePlatillo;
import com.microservice.food.repository.MenuRestaurantePlatilloRepository;
import com.microservice.food.service.MenuRestaurantePlatilloService;
import com.microservice.food.service.dto.MenuRestaurantePlatilloDTO;
import com.microservice.food.service.mapper.MenuRestaurantePlatilloMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MenuRestaurantePlatilloResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MenuRestaurantePlatilloResourceIT {

    private static final String DEFAULT_TIPO_PLATILLO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PLATILLO = "BBBBBBBBBB";

    @Autowired
    private MenuRestaurantePlatilloRepository menuRestaurantePlatilloRepository;

    @Mock
    private MenuRestaurantePlatilloRepository menuRestaurantePlatilloRepositoryMock;

    @Autowired
    private MenuRestaurantePlatilloMapper menuRestaurantePlatilloMapper;

    @Mock
    private MenuRestaurantePlatilloService menuRestaurantePlatilloServiceMock;

    @Autowired
    private MenuRestaurantePlatilloService menuRestaurantePlatilloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuRestaurantePlatilloMockMvc;

    private MenuRestaurantePlatillo menuRestaurantePlatillo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuRestaurantePlatillo createEntity(EntityManager em) {
        MenuRestaurantePlatillo menuRestaurantePlatillo = new MenuRestaurantePlatillo()
            .tipoPlatillo(DEFAULT_TIPO_PLATILLO);
        return menuRestaurantePlatillo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuRestaurantePlatillo createUpdatedEntity(EntityManager em) {
        MenuRestaurantePlatillo menuRestaurantePlatillo = new MenuRestaurantePlatillo()
            .tipoPlatillo(UPDATED_TIPO_PLATILLO);
        return menuRestaurantePlatillo;
    }

    @BeforeEach
    public void initTest() {
        menuRestaurantePlatillo = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuRestaurantePlatillo() throws Exception {
        int databaseSizeBeforeCreate = menuRestaurantePlatilloRepository.findAll().size();
        // Create the MenuRestaurantePlatillo
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO = menuRestaurantePlatilloMapper.toDto(menuRestaurantePlatillo);
        restMenuRestaurantePlatilloMockMvc.perform(post("/api/menu-restaurante-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestaurantePlatilloDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuRestaurantePlatillo in the database
        List<MenuRestaurantePlatillo> menuRestaurantePlatilloList = menuRestaurantePlatilloRepository.findAll();
        assertThat(menuRestaurantePlatilloList).hasSize(databaseSizeBeforeCreate + 1);
        MenuRestaurantePlatillo testMenuRestaurantePlatillo = menuRestaurantePlatilloList.get(menuRestaurantePlatilloList.size() - 1);
        assertThat(testMenuRestaurantePlatillo.getTipoPlatillo()).isEqualTo(DEFAULT_TIPO_PLATILLO);
    }

    @Test
    @Transactional
    public void createMenuRestaurantePlatilloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuRestaurantePlatilloRepository.findAll().size();

        // Create the MenuRestaurantePlatillo with an existing ID
        menuRestaurantePlatillo.setId(1L);
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO = menuRestaurantePlatilloMapper.toDto(menuRestaurantePlatillo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuRestaurantePlatilloMockMvc.perform(post("/api/menu-restaurante-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestaurantePlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRestaurantePlatillo in the database
        List<MenuRestaurantePlatillo> menuRestaurantePlatilloList = menuRestaurantePlatilloRepository.findAll();
        assertThat(menuRestaurantePlatilloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMenuRestaurantePlatillos() throws Exception {
        // Initialize the database
        menuRestaurantePlatilloRepository.saveAndFlush(menuRestaurantePlatillo);

        // Get all the menuRestaurantePlatilloList
        restMenuRestaurantePlatilloMockMvc.perform(get("/api/menu-restaurante-platillos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuRestaurantePlatillo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPlatillo").value(hasItem(DEFAULT_TIPO_PLATILLO)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMenuRestaurantePlatillosWithEagerRelationshipsIsEnabled() throws Exception {
        when(menuRestaurantePlatilloServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMenuRestaurantePlatilloMockMvc.perform(get("/api/menu-restaurante-platillos?eagerload=true"))
            .andExpect(status().isOk());

        verify(menuRestaurantePlatilloServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMenuRestaurantePlatillosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(menuRestaurantePlatilloServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMenuRestaurantePlatilloMockMvc.perform(get("/api/menu-restaurante-platillos?eagerload=true"))
            .andExpect(status().isOk());

        verify(menuRestaurantePlatilloServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMenuRestaurantePlatillo() throws Exception {
        // Initialize the database
        menuRestaurantePlatilloRepository.saveAndFlush(menuRestaurantePlatillo);

        // Get the menuRestaurantePlatillo
        restMenuRestaurantePlatilloMockMvc.perform(get("/api/menu-restaurante-platillos/{id}", menuRestaurantePlatillo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menuRestaurantePlatillo.getId().intValue()))
            .andExpect(jsonPath("$.tipoPlatillo").value(DEFAULT_TIPO_PLATILLO));
    }
    @Test
    @Transactional
    public void getNonExistingMenuRestaurantePlatillo() throws Exception {
        // Get the menuRestaurantePlatillo
        restMenuRestaurantePlatilloMockMvc.perform(get("/api/menu-restaurante-platillos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuRestaurantePlatillo() throws Exception {
        // Initialize the database
        menuRestaurantePlatilloRepository.saveAndFlush(menuRestaurantePlatillo);

        int databaseSizeBeforeUpdate = menuRestaurantePlatilloRepository.findAll().size();

        // Update the menuRestaurantePlatillo
        MenuRestaurantePlatillo updatedMenuRestaurantePlatillo = menuRestaurantePlatilloRepository.findById(menuRestaurantePlatillo.getId()).get();
        // Disconnect from session so that the updates on updatedMenuRestaurantePlatillo are not directly saved in db
        em.detach(updatedMenuRestaurantePlatillo);
        updatedMenuRestaurantePlatillo
            .tipoPlatillo(UPDATED_TIPO_PLATILLO);
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO = menuRestaurantePlatilloMapper.toDto(updatedMenuRestaurantePlatillo);

        restMenuRestaurantePlatilloMockMvc.perform(put("/api/menu-restaurante-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestaurantePlatilloDTO)))
            .andExpect(status().isOk());

        // Validate the MenuRestaurantePlatillo in the database
        List<MenuRestaurantePlatillo> menuRestaurantePlatilloList = menuRestaurantePlatilloRepository.findAll();
        assertThat(menuRestaurantePlatilloList).hasSize(databaseSizeBeforeUpdate);
        MenuRestaurantePlatillo testMenuRestaurantePlatillo = menuRestaurantePlatilloList.get(menuRestaurantePlatilloList.size() - 1);
        assertThat(testMenuRestaurantePlatillo.getTipoPlatillo()).isEqualTo(UPDATED_TIPO_PLATILLO);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuRestaurantePlatillo() throws Exception {
        int databaseSizeBeforeUpdate = menuRestaurantePlatilloRepository.findAll().size();

        // Create the MenuRestaurantePlatillo
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO = menuRestaurantePlatilloMapper.toDto(menuRestaurantePlatillo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuRestaurantePlatilloMockMvc.perform(put("/api/menu-restaurante-platillos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuRestaurantePlatilloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRestaurantePlatillo in the database
        List<MenuRestaurantePlatillo> menuRestaurantePlatilloList = menuRestaurantePlatilloRepository.findAll();
        assertThat(menuRestaurantePlatilloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuRestaurantePlatillo() throws Exception {
        // Initialize the database
        menuRestaurantePlatilloRepository.saveAndFlush(menuRestaurantePlatillo);

        int databaseSizeBeforeDelete = menuRestaurantePlatilloRepository.findAll().size();

        // Delete the menuRestaurantePlatillo
        restMenuRestaurantePlatilloMockMvc.perform(delete("/api/menu-restaurante-platillos/{id}", menuRestaurantePlatillo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MenuRestaurantePlatillo> menuRestaurantePlatilloList = menuRestaurantePlatilloRepository.findAll();
        assertThat(menuRestaurantePlatilloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
