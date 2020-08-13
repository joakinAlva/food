package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.OpcionesPlatillosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OpcionesPlatillos} and its DTO {@link OpcionesPlatillosDTO}.
 */
@Mapper(componentModel = "spring", uses = {OpcionPlatilloMapper.class})
public interface OpcionesPlatillosMapper extends EntityMapper<OpcionesPlatillosDTO, OpcionesPlatillos> {

    @Mapping(source = "opcionPlatillo.id", target = "opcionPlatilloId")
    OpcionesPlatillosDTO toDto(OpcionesPlatillos opcionesPlatillos);

    @Mapping(source = "opcionPlatilloId", target = "opcionPlatillo")
    OpcionesPlatillos toEntity(OpcionesPlatillosDTO opcionesPlatillosDTO);

    default OpcionesPlatillos fromId(Long id) {
        if (id == null) {
            return null;
        }
        OpcionesPlatillos opcionesPlatillos = new OpcionesPlatillos();
        opcionesPlatillos.setId(id);
        return opcionesPlatillos;
    }
}
