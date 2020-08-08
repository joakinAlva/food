package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class CategoriaPlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriaPlatilloDTO.class);
        CategoriaPlatilloDTO categoriaPlatilloDTO1 = new CategoriaPlatilloDTO();
        categoriaPlatilloDTO1.setId(1L);
        CategoriaPlatilloDTO categoriaPlatilloDTO2 = new CategoriaPlatilloDTO();
        assertThat(categoriaPlatilloDTO1).isNotEqualTo(categoriaPlatilloDTO2);
        categoriaPlatilloDTO2.setId(categoriaPlatilloDTO1.getId());
        assertThat(categoriaPlatilloDTO1).isEqualTo(categoriaPlatilloDTO2);
        categoriaPlatilloDTO2.setId(2L);
        assertThat(categoriaPlatilloDTO1).isNotEqualTo(categoriaPlatilloDTO2);
        categoriaPlatilloDTO1.setId(null);
        assertThat(categoriaPlatilloDTO1).isNotEqualTo(categoriaPlatilloDTO2);
    }
}
