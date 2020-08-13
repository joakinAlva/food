package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class ExtrasPlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtrasPlatilloDTO.class);
        ExtrasPlatilloDTO extrasPlatilloDTO1 = new ExtrasPlatilloDTO();
        extrasPlatilloDTO1.setId(1L);
        ExtrasPlatilloDTO extrasPlatilloDTO2 = new ExtrasPlatilloDTO();
        assertThat(extrasPlatilloDTO1).isNotEqualTo(extrasPlatilloDTO2);
        extrasPlatilloDTO2.setId(extrasPlatilloDTO1.getId());
        assertThat(extrasPlatilloDTO1).isEqualTo(extrasPlatilloDTO2);
        extrasPlatilloDTO2.setId(2L);
        assertThat(extrasPlatilloDTO1).isNotEqualTo(extrasPlatilloDTO2);
        extrasPlatilloDTO1.setId(null);
        assertThat(extrasPlatilloDTO1).isNotEqualTo(extrasPlatilloDTO2);
    }
}
