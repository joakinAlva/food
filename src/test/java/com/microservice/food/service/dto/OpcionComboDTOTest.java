package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionComboDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionComboDTO.class);
        OpcionComboDTO opcionComboDTO1 = new OpcionComboDTO();
        opcionComboDTO1.setId(1L);
        OpcionComboDTO opcionComboDTO2 = new OpcionComboDTO();
        assertThat(opcionComboDTO1).isNotEqualTo(opcionComboDTO2);
        opcionComboDTO2.setId(opcionComboDTO1.getId());
        assertThat(opcionComboDTO1).isEqualTo(opcionComboDTO2);
        opcionComboDTO2.setId(2L);
        assertThat(opcionComboDTO1).isNotEqualTo(opcionComboDTO2);
        opcionComboDTO1.setId(null);
        assertThat(opcionComboDTO1).isNotEqualTo(opcionComboDTO2);
    }
}
