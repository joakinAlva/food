package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class PlatillosComboTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlatillosCombo.class);
        PlatillosCombo platillosCombo1 = new PlatillosCombo();
        platillosCombo1.setId(1L);
        PlatillosCombo platillosCombo2 = new PlatillosCombo();
        platillosCombo2.setId(platillosCombo1.getId());
        assertThat(platillosCombo1).isEqualTo(platillosCombo2);
        platillosCombo2.setId(2L);
        assertThat(platillosCombo1).isNotEqualTo(platillosCombo2);
        platillosCombo1.setId(null);
        assertThat(platillosCombo1).isNotEqualTo(platillosCombo2);
    }
}
