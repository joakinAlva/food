package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.ComplementoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Complemento} and its DTO {@link ComplementoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ComplementoMapper extends EntityMapper<ComplementoDTO, Complemento> {


    @Mapping(target = "complementosPlatillos", ignore = true)
    @Mapping(target = "removeComplementosPlatillos", ignore = true)
    Complemento toEntity(ComplementoDTO complementoDTO);

    default Complemento fromId(Long id) {
        if (id == null) {
            return null;
        }
        Complemento complemento = new Complemento();
        complemento.setId(id);
        return complemento;
    }
}
