package com.microservice.food.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ComplementoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Complemento.class);
        Complemento complemento1 = new Complemento();
        complemento1.setId(1L);
        Complemento complemento2 = new Complemento();
        complemento2.setId(complemento1.getId());
        assertThat(complemento1).isEqualTo(complemento2);
        complemento2.setId(2L);
        assertThat(complemento1).isNotEqualTo(complemento2);
        complemento1.setId(null);
        assertThat(complemento1).isNotEqualTo(complemento2);
    }
}
