package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OpcionComboMapperTest {

    private OpcionComboMapper opcionComboMapper;

    /*@BeforeEach
    public void setUp() {
        opcionComboMapper = new OpcionComboMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(opcionComboMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(opcionComboMapper.fromId(null)).isNull();
    }
}
