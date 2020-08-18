package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionComboTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionCombo.class);
        OpcionCombo opcionCombo1 = new OpcionCombo();
        opcionCombo1.setId(1L);
        OpcionCombo opcionCombo2 = new OpcionCombo();
        opcionCombo2.setId(opcionCombo1.getId());
        assertThat(opcionCombo1).isEqualTo(opcionCombo2);
        opcionCombo2.setId(2L);
        assertThat(opcionCombo1).isNotEqualTo(opcionCombo2);
        opcionCombo1.setId(null);
        assertThat(opcionCombo1).isNotEqualTo(opcionCombo2);
    }
}
