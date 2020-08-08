package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class MenuRestaurantePlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRestaurantePlatillo.class);
        MenuRestaurantePlatillo menuRestaurantePlatillo1 = new MenuRestaurantePlatillo();
        menuRestaurantePlatillo1.setId(1L);
        MenuRestaurantePlatillo menuRestaurantePlatillo2 = new MenuRestaurantePlatillo();
        menuRestaurantePlatillo2.setId(menuRestaurantePlatillo1.getId());
        assertThat(menuRestaurantePlatillo1).isEqualTo(menuRestaurantePlatillo2);
        menuRestaurantePlatillo2.setId(2L);
        assertThat(menuRestaurantePlatillo1).isNotEqualTo(menuRestaurantePlatillo2);
        menuRestaurantePlatillo1.setId(null);
        assertThat(menuRestaurantePlatillo1).isNotEqualTo(menuRestaurantePlatillo2);
    }
}
