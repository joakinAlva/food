package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ExtrasPlatilloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtrasPlatillo.class);
        ExtrasPlatillo extrasPlatillo1 = new ExtrasPlatillo();
        extrasPlatillo1.setId(1L);
        ExtrasPlatillo extrasPlatillo2 = new ExtrasPlatillo();
        extrasPlatillo2.setId(extrasPlatillo1.getId());
        assertThat(extrasPlatillo1).isEqualTo(extrasPlatillo2);
        extrasPlatillo2.setId(2L);
        assertThat(extrasPlatillo1).isNotEqualTo(extrasPlatillo2);
        extrasPlatillo1.setId(null);
        assertThat(extrasPlatillo1).isNotEqualTo(extrasPlatillo2);
    }
}
