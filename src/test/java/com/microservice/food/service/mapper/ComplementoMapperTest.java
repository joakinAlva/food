package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComplementoMapperTest {

    private ComplementoMapper complementoMapper;

    @BeforeEach
    public void setUp() {
        complementoMapper = new ComplementoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(complementoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(complementoMapper.fromId(null)).isNull();
    }
}
