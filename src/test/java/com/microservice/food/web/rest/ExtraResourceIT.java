package com.microservice.food.web.rest;

import com.microservice.food.FoodApp;
import com.microservice.food.domain.Extra;
import com.microservice.food.repository.ExtraRepository;
import com.microservice.food.service.ExtraService;
import com.microservice.food.service.dto.ExtraDTO;
import com.microservice.food.service.mapper.ExtraMapper;

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
 * Integration tests for the {@link ExtraResource} REST controller.
 */
@SpringBootTest(classes = FoodApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExtraResourceIT {

    private static final String DEFAULT_EXTRA = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA = "BBBBBBBBBB";

    private static final String DEFAULT_PRECIO = "AAAAAAAAAA";
    private static final String UPDATED_PRECIO = "BBBBBBBBBB";

    @Autowired
    private ExtraRepository extraRepository;

    @Autowired
    private ExtraMapper extraMapper;

    @Autowired
    private ExtraService extraService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExtraMockMvc;

    private Extra extra;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Extra createEntity(EntityManager em) {
        Extra extra = new Extra()
            .extra(DEFAULT_EXTRA)
            .precio(DEFAULT_PRECIO);
        return extra;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Extra createUpdatedEntity(EntityManager em) {
        Extra extra = new Extra()
            .extra(UPDATED_EXTRA)
            .precio(UPDATED_PRECIO);
        return extra;
    }

    @BeforeEach
    public void initTest() {
        extra = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtra() throws Exception {
        int databaseSizeBeforeCreate = extraRepository.findAll().size();
        // Create the Extra
        ExtraDTO extraDTO = extraMapper.toDto(extra);
        restExtraMockMvc.perform(post("/api/extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraDTO)))
            .andExpect(status().isCreated());

        // Validate the Extra in the database
        List<Extra> extraList = extraRepository.findAll();
        assertThat(extraList).hasSize(databaseSizeBeforeCreate + 1);
        Extra testExtra = extraList.get(extraList.size() - 1);
        assertThat(testExtra.getExtra()).isEqualTo(DEFAULT_EXTRA);
        assertThat(testExtra.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createExtraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extraRepository.findAll().size();

        // Create the Extra with an existing ID
        extra.setId(1L);
        ExtraDTO extraDTO = extraMapper.toDto(extra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtraMockMvc.perform(post("/api/extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Extra in the database
        List<Extra> extraList = extraRepository.findAll();
        assertThat(extraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllExtras() throws Exception {
        // Initialize the database
        extraRepository.saveAndFlush(extra);

        // Get all the extraList
        restExtraMockMvc.perform(get("/api/extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extra.getId().intValue())))
            .andExpect(jsonPath("$.[*].extra").value(hasItem(DEFAULT_EXTRA)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO)));
    }
    
    @Test
    @Transactional
    public void getExtra() throws Exception {
        // Initialize the database
        extraRepository.saveAndFlush(extra);

        // Get the extra
        restExtraMockMvc.perform(get("/api/extras/{id}", extra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(extra.getId().intValue()))
            .andExpect(jsonPath("$.extra").value(DEFAULT_EXTRA))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO));
    }
    @Test
    @Transactional
    public void getNonExistingExtra() throws Exception {
        // Get the extra
        restExtraMockMvc.perform(get("/api/extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtra() throws Exception {
        // Initialize the database
        extraRepository.saveAndFlush(extra);

        int databaseSizeBeforeUpdate = extraRepository.findAll().size();

        // Update the extra
        Extra updatedExtra = extraRepository.findById(extra.getId()).get();
        // Disconnect from session so that the updates on updatedExtra are not directly saved in db
        em.detach(updatedExtra);
        updatedExtra
            .extra(UPDATED_EXTRA)
            .precio(UPDATED_PRECIO);
        ExtraDTO extraDTO = extraMapper.toDto(updatedExtra);

        restExtraMockMvc.perform(put("/api/extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraDTO)))
            .andExpect(status().isOk());

        // Validate the Extra in the database
        List<Extra> extraList = extraRepository.findAll();
        assertThat(extraList).hasSize(databaseSizeBeforeUpdate);
        Extra testExtra = extraList.get(extraList.size() - 1);
        assertThat(testExtra.getExtra()).isEqualTo(UPDATED_EXTRA);
        assertThat(testExtra.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingExtra() throws Exception {
        int databaseSizeBeforeUpdate = extraRepository.findAll().size();

        // Create the Extra
        ExtraDTO extraDTO = extraMapper.toDto(extra);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtraMockMvc.perform(put("/api/extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Extra in the database
        List<Extra> extraList = extraRepository.findAll();
        assertThat(extraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtra() throws Exception {
        // Initialize the database
        extraRepository.saveAndFlush(extra);

        int databaseSizeBeforeDelete = extraRepository.findAll().size();

        // Delete the extra
        restExtraMockMvc.perform(delete("/api/extras/{id}", extra.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Extra> extraList = extraRepository.findAll();
        assertThat(extraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
