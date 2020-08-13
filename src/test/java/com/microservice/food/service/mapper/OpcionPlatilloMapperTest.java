package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OpcionPlatilloMapperTest {

    private OpcionPlatilloMapper opcionPlatilloMapper;

    /*@BeforeEach
    public void setUp() {
        opcionPlatilloMapper = new OpcionPlatilloMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(opcionPlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(opcionPlatilloMapper.fromId(null)).isNull();
    }
}
