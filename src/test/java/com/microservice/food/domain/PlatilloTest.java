package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class PlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Platillo.class);
        Platillo platillo1 = new Platillo();
        platillo1.setId(1L);
        Platillo platillo2 = new Platillo();
        platillo2.setId(platillo1.getId());
        assertThat(platillo1).isEqualTo(platillo2);
        platillo2.setId(2L);
        assertThat(platillo1).isNotEqualTo(platillo2);
        platillo1.setId(null);
        assertThat(platillo1).isNotEqualTo(platillo2);
    }
}
