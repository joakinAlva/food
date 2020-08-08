package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class PlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlatilloDTO.class);
        PlatilloDTO platilloDTO1 = new PlatilloDTO();
        platilloDTO1.setId(1L);
        PlatilloDTO platilloDTO2 = new PlatilloDTO();
        assertThat(platilloDTO1).isNotEqualTo(platilloDTO2);
        platilloDTO2.setId(platilloDTO1.getId());
        assertThat(platilloDTO1).isEqualTo(platilloDTO2);
        platilloDTO2.setId(2L);
        assertThat(platilloDTO1).isNotEqualTo(platilloDTO2);
        platilloDTO1.setId(null);
        assertThat(platilloDTO1).isNotEqualTo(platilloDTO2);
    }
}
