package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoriaPlatilloMapperTest {

    private CategoriaPlatilloMapper categoriaPlatilloMapper;

   /* @BeforeEach
    public void setUp() {
        categoriaPlatilloMapper = new CategoriaPlatilloMapper();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categoriaPlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categoriaPlatilloMapper.fromId(null)).isNull();
    }
}
