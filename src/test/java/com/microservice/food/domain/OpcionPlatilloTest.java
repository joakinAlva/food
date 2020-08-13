package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionPlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionPlatillo.class);
        OpcionPlatillo opcionPlatillo1 = new OpcionPlatillo();
        opcionPlatillo1.setId(1L);
        OpcionPlatillo opcionPlatillo2 = new OpcionPlatillo();
        opcionPlatillo2.setId(opcionPlatillo1.getId());
        assertThat(opcionPlatillo1).isEqualTo(opcionPlatillo2);
        opcionPlatillo2.setId(2L);
        assertThat(opcionPlatillo1).isNotEqualTo(opcionPlatillo2);
        opcionPlatillo1.setId(null);
        assertThat(opcionPlatillo1).isNotEqualTo(opcionPlatillo2);
    }
}
