package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionesPlatillosDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionesPlatillosDTO.class);
        OpcionesPlatillosDTO opcionesPlatillosDTO1 = new OpcionesPlatillosDTO();
        opcionesPlatillosDTO1.setId(1L);
        OpcionesPlatillosDTO opcionesPlatillosDTO2 = new OpcionesPlatillosDTO();
        assertThat(opcionesPlatillosDTO1).isNotEqualTo(opcionesPlatillosDTO2);
        opcionesPlatillosDTO2.setId(opcionesPlatillosDTO1.getId());
        assertThat(opcionesPlatillosDTO1).isEqualTo(opcionesPlatillosDTO2);
        opcionesPlatillosDTO2.setId(2L);
        assertThat(opcionesPlatillosDTO1).isNotEqualTo(opcionesPlatillosDTO2);
        opcionesPlatillosDTO1.setId(null);
        assertThat(opcionesPlatillosDTO1).isNotEqualTo(opcionesPlatillosDTO2);
    }
}
