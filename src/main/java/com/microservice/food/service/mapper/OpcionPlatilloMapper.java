package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.OpcionPlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OpcionPlatillo} and its DTO {@link OpcionPlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OpcionPlatilloMapper extends EntityMapper<OpcionPlatilloDTO, OpcionPlatillo> {


    @Mapping(target = "opcionesPlatillos", ignore = true)
    @Mapping(target = "removeOpcionesPlatillos", ignore = true)
    OpcionPlatillo toEntity(OpcionPlatilloDTO opcionPlatilloDTO);

    default OpcionPlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        OpcionPlatillo opcionPlatillo = new OpcionPlatillo();
        opcionPlatillo.setId(id);
        return opcionPlatillo;
    }
}
