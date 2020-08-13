package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RestauranteMapperTest {

    private RestauranteMapper restauranteMapper;

   /* @BeforeEach
    public void setUp() {
        restauranteMapper = new RestauranteMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(restauranteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(restauranteMapper.fromId(null)).isNull();
    }
}
