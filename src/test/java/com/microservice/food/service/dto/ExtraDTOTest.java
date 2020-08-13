package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ExtraDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtraDTO.class);
        ExtraDTO extraDTO1 = new ExtraDTO();
        extraDTO1.setId(1L);
        ExtraDTO extraDTO2 = new ExtraDTO();
        assertThat(extraDTO1).isNotEqualTo(extraDTO2);
        extraDTO2.setId(extraDTO1.getId());
        assertThat(extraDTO1).isEqualTo(extraDTO2);
        extraDTO2.setId(2L);
        assertThat(extraDTO1).isNotEqualTo(extraDTO2);
        extraDTO1.setId(null);
        assertThat(extraDTO1).isNotEqualTo(extraDTO2);
    }
}
