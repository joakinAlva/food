package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuRestaurantePlatilloMapperTest {

    private MenuRestaurantePlatilloMapper menuRestaurantePlatilloMapper;

   /* @BeforeEach
    public void setUp() {
        menuRestaurantePlatilloMapper = new MenuRestaurantePlatilloMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(menuRestaurantePlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(menuRestaurantePlatilloMapper.fromId(null)).isNull();
    }
}
