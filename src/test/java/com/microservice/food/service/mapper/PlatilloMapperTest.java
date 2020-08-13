package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlatilloMapperTest {

    private PlatilloMapper platilloMapper;

    /*@BeforeEach
    public void setUp() {
        platilloMapper = new PlatilloMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(platilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(platilloMapper.fromId(null)).isNull();
    }
}
