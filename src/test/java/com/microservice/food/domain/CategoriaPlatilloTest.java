package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class CategoriaPlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriaPlatillo.class);
        CategoriaPlatillo categoriaPlatillo1 = new CategoriaPlatillo();
        categoriaPlatillo1.setId(1L);
        CategoriaPlatillo categoriaPlatillo2 = new CategoriaPlatillo();
        categoriaPlatillo2.setId(categoriaPlatillo1.getId());
        assertThat(categoriaPlatillo1).isEqualTo(categoriaPlatillo2);
        categoriaPlatillo2.setId(2L);
        assertThat(categoriaPlatillo1).isNotEqualTo(categoriaPlatillo2);
        categoriaPlatillo1.setId(null);
        assertThat(categoriaPlatillo1).isNotEqualTo(categoriaPlatillo2);
    }
}
