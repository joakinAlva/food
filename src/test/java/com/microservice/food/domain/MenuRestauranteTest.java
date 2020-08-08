package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class MenuRestauranteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRestaurante.class);
        MenuRestaurante menuRestaurante1 = new MenuRestaurante();
        menuRestaurante1.setId(1L);
        MenuRestaurante menuRestaurante2 = new MenuRestaurante();
        menuRestaurante2.setId(menuRestaurante1.getId());
        assertThat(menuRestaurante1).isEqualTo(menuRestaurante2);
        menuRestaurante2.setId(2L);
        assertThat(menuRestaurante1).isNotEqualTo(menuRestaurante2);
        menuRestaurante1.setId(null);
        assertThat(menuRestaurante1).isNotEqualTo(menuRestaurante2);
    }
}
