package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComplementosPlatilloMapperTest {

    private ComplementosPlatilloMapper complementosPlatilloMapper;

    @BeforeEach
    public void setUp() {
        complementosPlatilloMapper = new ComplementosPlatilloMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(complementosPlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(complementosPlatilloMapper.fromId(null)).isNull();
    }
}
