package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class PlatillosComboDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlatillosComboDTO.class);
        PlatillosComboDTO platillosComboDTO1 = new PlatillosComboDTO();
        platillosComboDTO1.setId(1L);
        PlatillosComboDTO platillosComboDTO2 = new PlatillosComboDTO();
        assertThat(platillosComboDTO1).isNotEqualTo(platillosComboDTO2);
        platillosComboDTO2.setId(platillosComboDTO1.getId());
        assertThat(platillosComboDTO1).isEqualTo(platillosComboDTO2);
        platillosComboDTO2.setId(2L);
        assertThat(platillosComboDTO1).isNotEqualTo(platillosComboDTO2);
        platillosComboDTO1.setId(null);
        assertThat(platillosComboDTO1).isNotEqualTo(platillosComboDTO2);
    }
}
