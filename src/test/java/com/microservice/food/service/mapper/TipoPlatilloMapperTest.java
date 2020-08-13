package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoPlatilloMapperTest {

    private TipoPlatilloMapper tipoPlatilloMapper;

    /*@BeforeEach
    public void setUp() {
        tipoPlatilloMapper = new TipoPlatilloMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tipoPlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoPlatilloMapper.fromId(null)).isNull();
    }
}
