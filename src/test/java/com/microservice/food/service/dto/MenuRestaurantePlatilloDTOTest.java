package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class MenuRestaurantePlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRestaurantePlatilloDTO.class);
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO1 = new MenuRestaurantePlatilloDTO();
        menuRestaurantePlatilloDTO1.setId(1L);
        MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO2 = new MenuRestaurantePlatilloDTO();
        assertThat(menuRestaurantePlatilloDTO1).isNotEqualTo(menuRestaurantePlatilloDTO2);
        menuRestaurantePlatilloDTO2.setId(menuRestaurantePlatilloDTO1.getId());
        assertThat(menuRestaurantePlatilloDTO1).isEqualTo(menuRestaurantePlatilloDTO2);
        menuRestaurantePlatilloDTO2.setId(2L);
        assertThat(menuRestaurantePlatilloDTO1).isNotEqualTo(menuRestaurantePlatilloDTO2);
        menuRestaurantePlatilloDTO1.setId(null);
        assertThat(menuRestaurantePlatilloDTO1).isNotEqualTo(menuRestaurantePlatilloDTO2);
    }
}
