package com.microservice.food.service.mapper;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExtrasPlatilloMapperTest {

    private ExtrasPlatilloMapper extrasPlatilloMapper;

    /*@BeforeEach
    public void setUp() {
        extrasPlatilloMapper = new ExtrasPlatilloMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(extrasPlatilloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(extrasPlatilloMapper.fromId(null)).isNull();
    }
}
