package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ComplementosPlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplementosPlatilloDTO.class);
        ComplementosPlatilloDTO complementosPlatilloDTO1 = new ComplementosPlatilloDTO();
        complementosPlatilloDTO1.setId(1L);
        ComplementosPlatilloDTO complementosPlatilloDTO2 = new ComplementosPlatilloDTO();
        assertThat(complementosPlatilloDTO1).isNotEqualTo(complementosPlatilloDTO2);
        complementosPlatilloDTO2.setId(complementosPlatilloDTO1.getId());
        assertThat(complementosPlatilloDTO1).isEqualTo(complementosPlatilloDTO2);
        complementosPlatilloDTO2.setId(2L);
        assertThat(complementosPlatilloDTO1).isNotEqualTo(complementosPlatilloDTO2);
        complementosPlatilloDTO1.setId(null);
        assertThat(complementosPlatilloDTO1).isNotEqualTo(complementosPlatilloDTO2);
    }
}
