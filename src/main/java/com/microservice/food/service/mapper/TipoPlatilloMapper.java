package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.TipoPlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoPlatillo} and its DTO {@link TipoPlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoPlatilloMapper extends EntityMapper<TipoPlatilloDTO, TipoPlatillo> {


    @Mapping(target = "platillos", ignore = true)
    @Mapping(target = "removePlatillos", ignore = true)
    TipoPlatillo toEntity(TipoPlatilloDTO tipoPlatilloDTO);

    default TipoPlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoPlatillo tipoPlatillo = new TipoPlatillo();
        tipoPlatillo.setId(id);
        return tipoPlatillo;
    }
}
