package com.microservice.food.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.microservice.food.web.rest.TestUtil;

public class TipoPlatilloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoPlatilloDTO.class);
        TipoPlatilloDTO tipoPlatilloDTO1 = new TipoPlatilloDTO();
        tipoPlatilloDTO1.setId(1L);
        TipoPlatilloDTO tipoPlatilloDTO2 = new TipoPlatilloDTO();
        assertThat(tipoPlatilloDTO1).isNotEqualTo(tipoPlatilloDTO2);
        tipoPlatilloDTO2.setId(tipoPlatilloDTO1.getId());
        assertThat(tipoPlatilloDTO1).isEqualTo(tipoPlatilloDTO2);
        tipoPlatilloDTO2.setId(2L);
        assertThat(tipoPlatilloDTO1).isNotEqualTo(tipoPlatilloDTO2);
        tipoPlatilloDTO1.setId(null);
        assertThat(tipoPlatilloDTO1).isNotEqualTo(tipoPlatilloDTO2);
    }
}
