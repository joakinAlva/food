package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ComplementosPlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplementosPlatillo.class);
        ComplementosPlatillo complementosPlatillo1 = new ComplementosPlatillo();
        complementosPlatillo1.setId(1L);
        ComplementosPlatillo complementosPlatillo2 = new ComplementosPlatillo();
        complementosPlatillo2.setId(complementosPlatillo1.getId());
        assertThat(complementosPlatillo1).isEqualTo(complementosPlatillo2);
        complementosPlatillo2.setId(2L);
        assertThat(complementosPlatillo1).isNotEqualTo(complementosPlatillo2);
        complementosPlatillo1.setId(null);
        assertThat(complementosPlatillo1).isNotEqualTo(complementosPlatillo2);
    }
}
