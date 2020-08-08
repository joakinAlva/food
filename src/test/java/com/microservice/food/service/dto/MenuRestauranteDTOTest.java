package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class MenuRestauranteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRestauranteDTO.class);
        MenuRestauranteDTO menuRestauranteDTO1 = new MenuRestauranteDTO();
        menuRestauranteDTO1.setId(1L);
        MenuRestauranteDTO menuRestauranteDTO2 = new MenuRestauranteDTO();
        assertThat(menuRestauranteDTO1).isNotEqualTo(menuRestauranteDTO2);
        menuRestauranteDTO2.setId(menuRestauranteDTO1.getId());
        assertThat(menuRestauranteDTO1).isEqualTo(menuRestauranteDTO2);
        menuRestauranteDTO2.setId(2L);
        assertThat(menuRestauranteDTO1).isNotEqualTo(menuRestauranteDTO2);
        menuRestauranteDTO1.setId(null);
        assertThat(menuRestauranteDTO1).isNotEqualTo(menuRestauranteDTO2);
    }
}
