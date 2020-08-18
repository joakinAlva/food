package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.OpcionComboDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OpcionCombo} and its DTO {@link OpcionComboDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OpcionComboMapper extends EntityMapper<OpcionComboDTO, OpcionCombo> {


    @Mapping(target = "platillosCombos", ignore = true)
    @Mapping(target = "removePlatillosCombo", ignore = true)
    OpcionCombo toEntity(OpcionComboDTO opcionComboDTO);

    default OpcionCombo fromId(Long id) {
        if (id == null) {
            return null;
        }
        OpcionCombo opcionCombo = new OpcionCombo();
        opcionCombo.setId(id);
        return opcionCombo;
    }
}
