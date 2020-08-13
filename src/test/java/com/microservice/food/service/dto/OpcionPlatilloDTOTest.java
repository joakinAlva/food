package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionPlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionPlatilloDTO.class);
        OpcionPlatilloDTO opcionPlatilloDTO1 = new OpcionPlatilloDTO();
        opcionPlatilloDTO1.setId(1L);
        OpcionPlatilloDTO opcionPlatilloDTO2 = new OpcionPlatilloDTO();
        assertThat(opcionPlatilloDTO1).isNotEqualTo(opcionPlatilloDTO2);
        opcionPlatilloDTO2.setId(opcionPlatilloDTO1.getId());
        assertThat(opcionPlatilloDTO1).isEqualTo(opcionPlatilloDTO2);
        opcionPlatilloDTO2.setId(2L);
        assertThat(opcionPlatilloDTO1).isNotEqualTo(opcionPlatilloDTO2);
        opcionPlatilloDTO1.setId(null);
        assertThat(opcionPlatilloDTO1).isNotEqualTo(opcionPlatilloDTO2);
    }
}
