package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ComplementoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplementoDTO.class);
        ComplementoDTO complementoDTO1 = new ComplementoDTO();
        complementoDTO1.setId(1L);
        ComplementoDTO complementoDTO2 = new ComplementoDTO();
        assertThat(complementoDTO1).isNotEqualTo(complementoDTO2);
        complementoDTO2.setId(complementoDTO1.getId());
        assertThat(complementoDTO1).isEqualTo(complementoDTO2);
        complementoDTO2.setId(2L);
        assertThat(complementoDTO1).isNotEqualTo(complementoDTO2);
        complementoDTO1.setId(null);
        assertThat(complementoDTO1).isNotEqualTo(complementoDTO2);
    }
}
