package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.PlatillosComboDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlatillosCombo} and its DTO {@link PlatillosComboDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlatilloMapper.class, OpcionComboMapper.class})
public interface PlatillosComboMapper extends EntityMapper<PlatillosComboDTO, PlatillosCombo> {

    @Mapping(source = "platillo.id", target = "platilloId")
    @Mapping(source = "opcionCombo.id", target = "opcionComboId")
    PlatillosComboDTO toDto(PlatillosCombo platillosCombo);

    @Mapping(source = "platilloId", target = "platillo")
    @Mapping(source = "opcionComboId", target = "opcionCombo")
    PlatillosCombo toEntity(PlatillosComboDTO platillosComboDTO);

    default PlatillosCombo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlatillosCombo platillosCombo = new PlatillosCombo();
        platillosCombo.setId(id);
        return platillosCombo;
    }
}
