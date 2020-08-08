package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class TipoPlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoPlatillo.class);
        TipoPlatillo tipoPlatillo1 = new TipoPlatillo();
        tipoPlatillo1.setId(1L);
        TipoPlatillo tipoPlatillo2 = new TipoPlatillo();
        tipoPlatillo2.setId(tipoPlatillo1.getId());
        assertThat(tipoPlatillo1).isEqualTo(tipoPlatillo2);
        tipoPlatillo2.setId(2L);
        assertThat(tipoPlatillo1).isNotEqualTo(tipoPlatillo2);
        tipoPlatillo1.setId(null);
        assertThat(tipoPlatillo1).isNotEqualTo(tipoPlatillo2);
    }
}
