package com.microservice.food.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExtraMapperTest {

    private ExtraMapper extraMapper;

    /*@BeforeEach
    public void setUp() {
        extraMapper = new ExtraMapperImpl();
    }*/

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(extraMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(extraMapper.fromId(null)).isNull();
    }
}
