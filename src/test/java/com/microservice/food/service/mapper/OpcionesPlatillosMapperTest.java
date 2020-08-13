package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OpcionesPlatillosMapperTest {

    private OpcionesPlatillosMapper opcionesPlatillosMapper;

    /*@BeforeEach
    public void setUp() {
        opcionesPlatillosMapper = new OpcionesPlatillosMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(opcionesPlatillosMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(opcionesPlatillosMapper.fromId(null)).isNull();
    }
}
