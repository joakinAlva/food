package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlatillosComboMapperTest {

    private PlatillosComboMapper platillosComboMapper;

    /*@BeforeEach
    public void setUp() {
        platillosComboMapper = new PlatillosComboMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(platillosComboMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(platillosComboMapper.fromId(null)).isNull();
    }
}
