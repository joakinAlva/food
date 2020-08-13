package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class OpcionesPlatillosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionesPlatillos.class);
        OpcionesPlatillos opcionesPlatillos1 = new OpcionesPlatillos();
        opcionesPlatillos1.setId(1L);
        OpcionesPlatillos opcionesPlatillos2 = new OpcionesPlatillos();
        opcionesPlatillos2.setId(opcionesPlatillos1.getId());
        assertThat(opcionesPlatillos1).isEqualTo(opcionesPlatillos2);
        opcionesPlatillos2.setId(2L);
        assertThat(opcionesPlatillos1).isNotEqualTo(opcionesPlatillos2);
        opcionesPlatillos1.setId(null);
        assertThat(opcionesPlatillos1).isNotEqualTo(opcionesPlatillos2);
    }
}
